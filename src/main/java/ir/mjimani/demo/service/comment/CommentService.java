package ir.mjimani.demo.service.comment;

import ir.mjimani.demo.controller.comment.dto.ReqCreateCommentDto;
import ir.mjimani.demo.controller.comment.dto.ReqGetPageCommentDto;
import ir.mjimani.demo.domain.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {
    Comment create(ReqCreateCommentDto reqCreateCommentDto);

    Page<Comment> getPageForAdmin(ReqGetPageCommentDto reqGetPageCommentDto);

    Boolean updateConfirm(String id, Boolean confirm);
}
