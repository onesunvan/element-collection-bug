package hello.repositories;

import hello.entities.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomARepositoryImpl implements CustomARepository {
    private static Logger LOG = LoggerFactory.getLogger(CustomARepository.class);

    @PersistenceContext
    private EntityManager em;

    @Override
//    @Transactional
    public void foo() {
        em.getTransaction().begin();
        LOG.info("{}", em.hashCode());
        em.persist(new A("a1"));
        em.persist(new A("a2"));
        em.persist(new A("a3"));
        em.persist(new A("a4"));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        em.getTransaction().commit();
    }

    @Override
//    @Transactional
    public void bar() {
        em.getTransaction().begin();
        LOG.info("{}", em.hashCode());
        List<A> as = em.createQuery("select a1 from A a1", A.class).getResultList();
        LOG.info("{}", as);
        em.getTransaction().commit();
    }
}
