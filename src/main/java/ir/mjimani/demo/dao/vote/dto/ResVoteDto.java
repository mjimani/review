package ir.mjimani.demo.dao.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResVoteDto {

    public enum FN {
        productId, avgVote
    }

    private String productId;
    private Float avgVote;
}
