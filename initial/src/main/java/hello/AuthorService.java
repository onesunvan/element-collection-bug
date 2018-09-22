package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Phaser;

@Service
public class AuthorService {
    private static Logger LOG = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;

    private Phaser phaser = new Phaser();

    public void register(int n) {
        phaser.bulkRegister(n);
    }

    public void foo() {
        LOG.info("start foo");

        phaser.arriveAndAwaitAdvance();
        Author author = authorRepository.findById(1L).get();
        LOG.info("findById - {}", author);

        phaser.arriveAndAwaitAdvance();
        authorRepository.save(author);
        LOG.info("saved author");

        phaser.arriveAndDeregister();
        LOG.info("finish foo");
    }
}
