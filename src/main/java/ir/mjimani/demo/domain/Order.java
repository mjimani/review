package ir.mjimani.demo.domain;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Order.ENTITY_NAME)
public class Order extends BaseDomain {

    @Transient
    public static final String ENTITY_NAME = "orders";

    public enum FN {
        totalPrice, productIds
    }

    private Long totalPrice;

    private List<String> productIds;

    public Order(String id) {
        this.id = id;
    }
}
