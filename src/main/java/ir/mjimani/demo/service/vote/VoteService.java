package ir.mjimani.demo.service.vote;

import ir.mjimani.demo.controller.vote.dto.ReqCreateVoteDto;
import ir.mjimani.demo.controller.vote.dto.ReqGetPageVoteDto;
import ir.mjimani.demo.domain.Vote;
import org.springframework.data.domain.Page;

public interface VoteService {
    Vote create(ReqCreateVoteDto reqCreateVoteDto);

    Page<Vote> getPageForAdmin(ReqGetPageVoteDto reqGetPageVoteDto);

    Boolean updateConfirm(String id, Boolean confirm);
}
