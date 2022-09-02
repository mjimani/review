package ir.mjimani.demo.service.order;

import ir.mjimani.demo.controller.order.dto.ReqCreateOrderDto;
import ir.mjimani.demo.dao.order.OrderDao;
import ir.mjimani.demo.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderDao orderDao;

    @Override
    public Order create(ReqCreateOrderDto reqCreateOrderDto) {
        reqCreateOrderDto.validation();
        Order order = reqCreateOrderDto.toOrder();
        order.init();
        order = orderDao.create(order);

        return new Order(order.getId());
    }
}
