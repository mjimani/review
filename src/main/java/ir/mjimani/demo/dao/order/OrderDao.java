package ir.mjimani.demo.dao.order;

import ir.mjimani.demo.domain.Order;

public interface OrderDao {

    Order create(Order order);

    Boolean checkBuyerByProductIdUserId(String productId, String userId);
}
