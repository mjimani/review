package ir.mjimani.demo.repository;

import ir.mjimani.demo.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {
}
