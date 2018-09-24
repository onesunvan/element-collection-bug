package hello;

import java.util.List;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select customer from Customer customer where customer.id = :id")
    Customer findOneAndLock(@Param("id") Long id);

}