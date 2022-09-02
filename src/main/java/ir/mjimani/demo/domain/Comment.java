package ir.mjimani.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Comment.ENTITY_NAME)
public class Comment extends BaseDomain {

    @Transient
    public static final String ENTITY_NAME = "comment";

    public enum FN {
        text, isConfirmed, productId
    }

    private String text;
    private Boolean isConfirmed;
    @Indexed
    private String productId;

    public Comment(String id) {
        this.id = id;
    }
}
