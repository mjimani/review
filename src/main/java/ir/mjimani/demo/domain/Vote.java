package ir.mjimani.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Vote.ENTITY_NAME)
public class Vote extends BaseDomain {

    @Transient
    public static final String ENTITY_NAME = "vote";

    public enum FN {
        vote, isConfirmed, productId
    }

    private Integer vote;
    private Boolean isConfirmed;
    private String productId;

    public Vote(String id) {
        this.id = id;
    }
}

