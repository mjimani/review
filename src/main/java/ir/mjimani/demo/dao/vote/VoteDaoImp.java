package ir.mjimani.demo.dao.vote;

import com.mongodb.client.result.UpdateResult;
import ir.mjimani.demo.controller.vote.dto.ReqGetPageVoteDto;
import ir.mjimani.demo.dao.vote.dto.ResVoteDto;
import ir.mjimani.demo.domain.BaseDomain;
import ir.mjimani.demo.domain.Comment;
import ir.mjimani.demo.domain.Vote;
import ir.mjimani.demo.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VoteDaoImp implements VoteDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public Vote create(Vote vote) {
        return mongoTemplate.save(vote);
    }

    @Override
    public List<ResVoteDto> getAvgVoteByProductIds(List<String> productIds) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(new Criteria().andOperator(
                        Criteria.where(Vote.FN.productId.name()).in(productIds),
                        Criteria.where(Vote.FN.isConfirmed.name()).is(true)
                )),
                Aggregation.group(Comment.FN.productId.name())
                        .avg(Vote.FN.vote.name()).as(ResVoteDto.FN.avgVote.name()),
                Aggregation
                        .project(ResVoteDto.FN.avgVote.name())
                        .and("_id").as(ResVoteDto.FN.productId.name())
        );
        return mongoTemplate.aggregate(aggregation, Vote.class, ResVoteDto.class).getMappedResults();
    }

    @Override
    public Page<Vote> getPageForAdmin(ReqGetPageVoteDto reqGetPageVoteDto) {
        PageRequest pageRequest = PageRequest.of(reqGetPageVoteDto.getPage(), reqGetPageVoteDto.getSize());
        Query query = new Query().with(pageRequest);
        if (reqGetPageVoteDto.getIsConfirmed() != null) {
            query.addCriteria(Criteria.where(Vote.FN.isConfirmed.name()).is(reqGetPageVoteDto.getIsConfirmed()));
        }
        List<Vote> comments = mongoTemplate.find(query, Vote.class);
        long count = 0L;
        if (!comments.isEmpty()) {
            count = mongoTemplate.count(query, Vote.class);
        }
        return new PageImpl<>(comments, pageRequest, count);
    }

    @Override
    public Boolean updateConfirm(String id, Boolean confirm) {
        Query query = new Query()
                .addCriteria(Criteria.where(BaseDomain.BFN.id.name()).is(id));
        Update update = new Update()
                .set(Vote.FN.isConfirmed.name(), confirm);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Vote.class);

        if (updateResult.getMatchedCount() <= 0) {
            throw new NotFoundException("Vote not found.");
        }
        return updateResult.getModifiedCount() > 0;
    }
}
