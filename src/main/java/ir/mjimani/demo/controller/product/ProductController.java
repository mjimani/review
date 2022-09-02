package ir.mjimani.demo.controller.product;

import ir.mjimani.demo.controller.product.dto.*;
import ir.mjimani.demo.domain.Product;
import ir.mjimani.demo.model.Response;
import ir.mjimani.demo.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product/")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Response<Product>> create(@RequestBody ReqCreateProductDto reqCreateProductDto) {
        return ResponseEntity.ok().body(new Response<>(productService.create(reqCreateProductDto)));
    }

    /**
     * For Users
     */
    @GetMapping("/review")
    public ResponseEntity<Response<Page<ResReviewPageDto>>> getReviewPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok().body(new Response<>(productService.getReviewPage(page, size)));
    }

    /**
     * For Users
     * @param productId ,
     * @return {@link Product}
     */
    @GetMapping("/detail")
    public ResponseEntity<Response<Product>> getDetail(@RequestParam("productId") String productId) {
        return ResponseEntity.ok().body(new Response<>(productService.getDetail(productId)));
    }


}
