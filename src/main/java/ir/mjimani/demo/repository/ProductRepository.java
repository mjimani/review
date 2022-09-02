package ir.mjimani.demo.repository;

import ir.mjimani.demo.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
