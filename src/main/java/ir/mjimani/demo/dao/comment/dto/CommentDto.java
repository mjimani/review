package ir.mjimani.demo.dao.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    public enum FN {
        text, creatorId, creationDate
    }

    private String text;
    private String creatorId;
    private Long creationDate;
}
