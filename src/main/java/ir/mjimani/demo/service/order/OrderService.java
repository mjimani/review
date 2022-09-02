package ir.mjimani.demo.service.order;

import ir.mjimani.demo.controller.order.dto.ReqCreateOrderDto;
import ir.mjimani.demo.domain.Order;

public interface OrderService {
    Order create(ReqCreateOrderDto reqCreateOrderDto);
}
