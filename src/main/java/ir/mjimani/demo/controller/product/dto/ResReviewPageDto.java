package ir.mjimani.demo.controller.product.dto;

import ir.mjimani.demo.dao.comment.dto.CommentDto;
import ir.mjimani.demo.domain.enums.PersonKind;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResReviewPageDto {
    private String id;
    private String name;
    private Long price;
    private Boolean hasComment; // Possibility of comment
    private Boolean hasVote; // Possibility of vote
    private PersonKind reviewPersonKind;
    private Long totalComments = 0L;
    private List<CommentDto> lastComments;
    private Float avgVote;
}
