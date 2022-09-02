package ir.mjimani.demo.dao.comment;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import ir.mjimani.demo.controller.comment.dto.ReqGetPageCommentDto;
import ir.mjimani.demo.dao.comment.dto.CommentDto;
import ir.mjimani.demo.dao.comment.dto.ResCommentsDto;
import ir.mjimani.demo.domain.BaseDomain;
import ir.mjimani.demo.domain.Comment;
import ir.mjimani.demo.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDaoImp implements CommentDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public Comment create(Comment comment) {
        return mongoTemplate.save(comment);
    }

    @Override
    public List<ResCommentsDto> getCommentsByProductIds(List<String> productIds) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(new Criteria().andOperator(
                        Criteria.where(Comment.FN.productId.name()).in(productIds),
                        Criteria.where(Comment.FN.isConfirmed.name()).is(true)
                )),
                Aggregation.sort(Sort.Direction.DESC, BaseDomain.BFN.creationDate.name()),
                Aggregation
                        .group(Comment.FN.productId.name())
                        .count().as(ResCommentsDto.FN.count.name())
                        .first(Comment.FN.productId.name()).as(ResCommentsDto.FN.productId.name())
                        .push(new BasicDBObject(CommentDto.FN.text.name(), "$" + Comment.FN.text.name())
                                .append(CommentDto.FN.creatorId.name(), "$" + BaseDomain.BFN.creatorId.name())
                                .append(CommentDto.FN.creationDate.name(), "$" + BaseDomain.BFN.creationDate.name())
                        )
                        .as(ResCommentsDto.FN.lastComments.name()),
                Aggregation
                        .project(ResCommentsDto.FN.count.name(), Comment.FN.productId.name())
                        .and(ResCommentsDto.FN.lastComments.name()).slice(3)
        );
        return mongoTemplate.aggregate(aggregation, Comment.class, ResCommentsDto.class).getMappedResults();

    }

    @Override
    public Page<Comment> getPageForAdmin(ReqGetPageCommentDto reqGetPageCommentDto) {
        PageRequest pageRequest = PageRequest.of(reqGetPageCommentDto.getPage(), reqGetPageCommentDto.getSize());
        Query query = new Query().with(pageRequest);
        if (reqGetPageCommentDto.getIsConfirmed() != null) {
            query.addCriteria(Criteria.where(Comment.FN.isConfirmed.name()).is(reqGetPageCommentDto.getIsConfirmed()));
        }
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        long count = 0L;
        if (!comments.isEmpty()) {
            count = mongoTemplate.count(query, Comment.class);
        }
        return new PageImpl<>(comments, pageRequest, count);
    }

    @Override
    public Boolean updateConfirm(String id, Boolean confirm) {
        Query query = new Query()
                .addCriteria(Criteria.where(BaseDomain.BFN.id.name()).is(id));
        Update update = new Update()
                .set(Comment.FN.isConfirmed.name(), confirm);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Comment.class);

        if (updateResult.getMatchedCount() <= 0) {
            throw new NotFoundException("Comment not found.");
        }
        return updateResult.getModifiedCount() > 0;
    }
}
