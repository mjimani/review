package ir.mjimani.demo.dao.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResCommentsDto {

    public enum FN {
        count, productId, lastComments
    }

    private Long count;

    private String productId;
    private List<CommentDto> lastComments;
}
