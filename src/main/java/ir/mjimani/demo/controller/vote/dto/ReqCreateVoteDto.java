package ir.mjimani.demo.controller.vote.dto;

import ir.mjimani.demo.domain.Vote;
import ir.mjimani.demo.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqCreateVoteDto {
    private String productId;
    private Integer vote;

    public void validation() {
        if (productId == null || productId.isEmpty()) {
            throw new InvalidInputException("Product id is missing!");
        }
        if (vote == null) {
            throw new InvalidInputException("Vote is missing!");
        }
    }

    public Vote toVote() {
        return Vote
                .builder()
                .productId(productId)
                .vote(vote)
                .build();
    }
}
