package ir.mjimani.demo.service.comment;

import ir.mjimani.demo.controller.comment.dto.ReqCreateCommentDto;
import ir.mjimani.demo.controller.comment.dto.ReqGetPageCommentDto;
import ir.mjimani.demo.dao.comment.CommentDao;
import ir.mjimani.demo.dao.order.OrderDao;
import ir.mjimani.demo.dao.product.ProductDao;
import ir.mjimani.demo.domain.Comment;
import ir.mjimani.demo.domain.Product;
import ir.mjimani.demo.domain.enums.PersonKind;
import ir.mjimani.demo.exception.NotFoundException;
import ir.mjimani.demo.exception.UnavailableProcessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {

    private final CommentDao commentDao;
    private final ProductDao productDao;
    private final OrderDao orderDao;

    @Override
    public Comment create(ReqCreateCommentDto reqCreateCommentDto) {
        reqCreateCommentDto.validation();

        Product product = productDao.findOneByProductIdAndIsPublished(reqCreateCommentDto.getProductId());

        if (product == null){
            throw new NotFoundException("Product not found.");
        }

        if (!product.getHasComment()){
            throw new UnavailableProcessException("Comment is not enable.");
        }

        Comment comment = reqCreateCommentDto.toComment();
        comment.init();

        if (product.getReviewPersonKind() == PersonKind.BUYER){
            Boolean isBuyer = orderDao.checkBuyerByProductIdUserId(reqCreateCommentDto.getProductId(),comment.getCreatorId());
            if (!isBuyer){
                throw new UnavailableProcessException("You can not create a comment.");
            }
        }

        comment = commentDao.create(comment);

        return new Comment(comment.getId());
    }

    @Override
    public Page<Comment> getPageForAdmin(ReqGetPageCommentDto reqGetPageCommentDto) {
        return commentDao.getPageForAdmin(reqGetPageCommentDto);
    }

    @Override
    public Boolean updateConfirm(String id, Boolean confirm) {
        return commentDao.updateConfirm(id,confirm);
    }
}
