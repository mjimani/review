package ir.mjimani.demo.service.vote;

import ir.mjimani.demo.controller.vote.dto.ReqCreateVoteDto;
import ir.mjimani.demo.controller.vote.dto.ReqGetPageVoteDto;
import ir.mjimani.demo.dao.order.OrderDao;
import ir.mjimani.demo.dao.product.ProductDao;
import ir.mjimani.demo.dao.vote.VoteDao;
import ir.mjimani.demo.domain.Product;
import ir.mjimani.demo.domain.Vote;
import ir.mjimani.demo.domain.enums.PersonKind;
import ir.mjimani.demo.exception.NotFoundException;
import ir.mjimani.demo.exception.UnavailableProcessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteServiceImp implements VoteService {

    private final VoteDao voteDao;
    private final ProductDao productDao;
    private final OrderDao orderDao;

    @Override
    public Vote create(ReqCreateVoteDto reqCreateVoteDto) {
        reqCreateVoteDto.validation();

        Product product = productDao.findOneByProductIdAndIsPublished(reqCreateVoteDto.getProductId());

        if (product == null){
            throw new NotFoundException("Product not found.");
        }

        if (!product.getHasVote()){
            throw new UnavailableProcessException("Vote is not enable.");
        }

        Vote vote = reqCreateVoteDto.toVote();
        vote.init();

        if (product.getReviewPersonKind() == PersonKind.BUYER){
            Boolean isBuyer = orderDao.checkBuyerByProductIdUserId(reqCreateVoteDto.getProductId(),vote.getCreatorId());
            if (!isBuyer){
                throw new UnavailableProcessException("You can not create a vote.");
            }
        }

        vote = voteDao.create(vote);

        return new Vote(vote.getId());
    }

    @Override
    public Page<Vote> getPageForAdmin(ReqGetPageVoteDto reqGetPageVoteDto) {
        return voteDao.getPageForAdmin(reqGetPageVoteDto);
    }

    @Override
    public Boolean updateConfirm(String id, Boolean confirm) {
        return voteDao.updateConfirm(id,confirm);
    }
}
