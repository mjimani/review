package ir.mjimani.demo.controller.order.dto;

import ir.mjimani.demo.domain.Order;
import ir.mjimani.demo.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqCreateOrderDto {
    private List<String> productIds;

    public void validation() {
        if (productIds == null || productIds.isEmpty()) {
            throw new InvalidInputException("Products are missing!");
        }
    }

    public Order toOrder() {
        return Order
                .builder()
                .productIds(productIds)
                .build();
    }
}
