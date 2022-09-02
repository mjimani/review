package ir.mjimani.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mjimani.demo.domain.enums.PersonKind;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Product.ENTITY_NAME)
public class Product extends BaseDomain {

    @Transient
    public static final String ENTITY_NAME = "product";

    public enum FN {
        name, price, isPublish, hasComment, hasVote, reviewPersonKind, providerId
    }

    private String name;
    private Long price;
    private Boolean isPublish;
    private Boolean hasComment; // Possibility of comment
    private Boolean hasVote; // Possibility of vote
    private PersonKind reviewPersonKind;

    private String providerId;

    public Product(String id) {
        this.id = id;
    }
}

