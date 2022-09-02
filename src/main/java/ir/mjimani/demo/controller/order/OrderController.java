package ir.mjimani.demo.controller.order;

import ir.mjimani.demo.controller.order.dto.ReqCreateOrderDto;
import ir.mjimani.demo.domain.Order;
import ir.mjimani.demo.model.Response;
import ir.mjimani.demo.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<Response<Order>> create(@RequestBody ReqCreateOrderDto reqCreateOrderDto) {
        return ResponseEntity.ok().body(new Response<>(orderService.create(reqCreateOrderDto)));
    }
}
