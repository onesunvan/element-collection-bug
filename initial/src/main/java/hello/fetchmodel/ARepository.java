package hello.fetchmodel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ARepository extends CrudRepository<A, Long>, CustomARepository {
    @Query("select a1 from A a1 left join fetch a1.bs b1 left join fetch b1.cs c1")
    List<A> customQuery();
}
