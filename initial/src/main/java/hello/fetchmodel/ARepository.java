package hello.fetchmodel;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ARepository extends CrudRepository<A, Long>, CustomARepository {
}
