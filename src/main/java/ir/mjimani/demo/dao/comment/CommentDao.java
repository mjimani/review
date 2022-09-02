package ir.mjimani.demo.dao.comment;

import ir.mjimani.demo.controller.comment.dto.ReqGetPageCommentDto;
import ir.mjimani.demo.dao.comment.dto.ResCommentsDto;
import ir.mjimani.demo.domain.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentDao {
    Comment create(Comment comment);

    List<ResCommentsDto> getCommentsByProductIds(List<String> productIds);

    Page<Comment> getPageForAdmin(ReqGetPageCommentDto reqGetPageCommentDto);

    Boolean updateConfirm(String id, Boolean confirm);
}
