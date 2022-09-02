package ir.mjimani.demo.controller.vote;

import ir.mjimani.demo.controller.vote.dto.ReqCreateVoteDto;
import ir.mjimani.demo.controller.vote.dto.ReqGetPageVoteDto;
import ir.mjimani.demo.domain.Vote;
import ir.mjimani.demo.model.Response;
import ir.mjimani.demo.service.vote.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vote/")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("create")
    public ResponseEntity<Response<Vote>> create(@RequestBody ReqCreateVoteDto reqCreateVoteDto) {
        return ResponseEntity.ok().body(new Response<>(voteService.create(reqCreateVoteDto)));
    }

    @GetMapping("/admin/get-page")
    public ResponseEntity<Response<Page<Vote>>> getPageForAdmin(ReqGetPageVoteDto reqGetPageVoteDto) {
        return ResponseEntity.ok().body(new Response<>(voteService.getPageForAdmin(reqGetPageVoteDto)));
    }

    @PatchMapping("/admin/update-confirm")
    public ResponseEntity<Response<Boolean>> updateConfirm(@RequestParam("id") String id, @RequestParam("confirm") Boolean confirm) {
        return ResponseEntity.ok().body(new Response<>(voteService.updateConfirm(id, confirm)));
    }

}
