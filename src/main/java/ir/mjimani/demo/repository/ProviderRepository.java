package ir.mjimani.demo.repository;

import ir.mjimani.demo.domain.Provider;
import org.springframework.data.repository.CrudRepository;

public interface ProviderRepository extends CrudRepository<Provider, String> {
}
