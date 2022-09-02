package ir.mjimani.demo.controller.product.dto;

import ir.mjimani.demo.domain.Product;
import ir.mjimani.demo.domain.enums.PersonKind;
import ir.mjimani.demo.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqCreateProductDto {
    private String name;
    private Long price;
    private Boolean isPublish = true;
    private Boolean hasComment = true;
    private Boolean hasVote = true;
    private PersonKind reviewPersonKind = PersonKind.EVERYONE;
    private String providerId;

    public void validation(){
        if (name == null || name.isEmpty()){
            throw new InvalidInputException("Name is missing!");
        }
        if (price == null){
            throw new InvalidInputException("Price is missing!");
        }
        if (providerId == null || providerId.isEmpty()){
            throw new InvalidInputException("Provider id is missing!");
        }
    }
    public Product toProduct() {
        return Product
                .builder()
                .name(name)
                .price(price)
                .isPublish(isPublish)
                .hasComment(hasComment)
                .hasVote(hasVote)
                .reviewPersonKind(reviewPersonKind)
                .providerId(providerId)
                .build();
    }
}
