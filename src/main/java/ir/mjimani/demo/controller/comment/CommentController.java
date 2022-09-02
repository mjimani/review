package ir.mjimani.demo.controller.comment;

import ir.mjimani.demo.controller.comment.dto.ReqCreateCommentDto;
import ir.mjimani.demo.controller.comment.dto.ReqGetPageCommentDto;
import ir.mjimani.demo.domain.Comment;
import ir.mjimani.demo.model.Response;
import ir.mjimani.demo.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comment/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("create")
    public ResponseEntity<Response<Comment>> create(@RequestBody ReqCreateCommentDto reqCreateCommentDto) {
        return ResponseEntity.ok().body(new Response<>(commentService.create(reqCreateCommentDto)));
    }

    @GetMapping("/admin/get-page")
    public ResponseEntity<Response<Page<Comment>>> getPageForAdmin(ReqGetPageCommentDto reqGetPageCommentDto) {
        return ResponseEntity.ok().body(new Response<>(commentService.getPageForAdmin(reqGetPageCommentDto)));
    }

    @PatchMapping("/admin/update-confirm")
    public ResponseEntity<Response<Boolean>> updateConfirm(@RequestParam("id") String id, @RequestParam("confirm") Boolean confirm) {
        return ResponseEntity.ok().body(new Response<>(commentService.updateConfirm(id, confirm)));
    }
}
