package ir.mjimani.demo.controller.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqGetPageCommentDto {
    private Integer page = 0;
    private Integer size = 10;
    private Boolean isConfirmed;
}
