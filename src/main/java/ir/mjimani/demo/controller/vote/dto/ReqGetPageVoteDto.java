package ir.mjimani.demo.controller.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqGetPageVoteDto {
    private Integer page = 0;
    private Integer size = 10;
    private Boolean isConfirmed;
}
