package ir.mjimani.demo.repository;

import ir.mjimani.demo.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, String> {
}
