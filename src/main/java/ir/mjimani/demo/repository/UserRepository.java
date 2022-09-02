package ir.mjimani.demo.repository;

import ir.mjimani.demo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
