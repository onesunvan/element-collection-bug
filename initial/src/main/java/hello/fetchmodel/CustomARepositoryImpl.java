package hello.fetchmodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CustomARepositoryImpl implements CustomARepository {

    private static final Logger LOG = LoggerFactory.getLogger(CustomARepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void foo() {
        LOG.info("HI IVAN");
        List<B> bs = IntStream.range(0, 5).mapToObj(i -> new B(new Long(i), Integer.toString(i))).collect(Collectors.toList());
        A a = new A(1L, bs , "1");
        em.persist(a);
    }
}
