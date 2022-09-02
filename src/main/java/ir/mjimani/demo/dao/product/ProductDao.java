package ir.mjimani.demo.dao.product;

import ir.mjimani.demo.controller.product.dto.*;
import ir.mjimani.demo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductDao {
    Product create(Product product);

    Page<ResReviewPageDto> getPage(PageRequest pageRequest);

    Product findOneByProductIdAndIsPublished(String productId);

}
