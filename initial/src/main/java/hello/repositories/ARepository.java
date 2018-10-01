package hello.repositories;

import hello.entities.A;
import org.springframework.data.repository.CrudRepository;

public interface ARepository extends CrudRepository<A, Long>, CustomARepository {
}
