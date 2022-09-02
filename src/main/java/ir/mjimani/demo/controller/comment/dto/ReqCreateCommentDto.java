package ir.mjimani.demo.controller.comment.dto;

import ir.mjimani.demo.domain.Comment;
import ir.mjimani.demo.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqCreateCommentDto {
    private String productId;
    private String text;

    public void validation() {
        if (productId == null || productId.isEmpty()) {
            throw new InvalidInputException("Product id is missing!");
        }
        if (text == null || text.isEmpty()) {
            throw new InvalidInputException("Text is missing!");
        }
    }

    public Comment toComment() {
        return Comment
                .builder()
                .productId(productId)
                .text(text)
                .isConfirmed(false)
                .build();
    }
}
