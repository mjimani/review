package ir.mjimani.demo.repository;

import ir.mjimani.demo.domain.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, String> {
}
