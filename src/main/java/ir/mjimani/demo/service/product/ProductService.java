package ir.mjimani.demo.service.product;

import ir.mjimani.demo.controller.product.dto.*;
import ir.mjimani.demo.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product create(ReqCreateProductDto reqCreateProductDto);

    Page<ResReviewPageDto> getReviewPage(Integer page, Integer size);

    Product getDetail(String productId);
}
