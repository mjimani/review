package ir.mjimani.demo.dao.vote;

import ir.mjimani.demo.controller.vote.dto.ReqGetPageVoteDto;
import ir.mjimani.demo.dao.vote.dto.ResVoteDto;
import ir.mjimani.demo.domain.Vote;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VoteDao {
    Vote create(Vote vote);

    List<ResVoteDto> getAvgVoteByProductIds(List<String> productIds);

    Page<Vote> getPageForAdmin(ReqGetPageVoteDto reqGetPageVoteDto);

    Boolean updateConfirm(String id, Boolean confirm);
}
