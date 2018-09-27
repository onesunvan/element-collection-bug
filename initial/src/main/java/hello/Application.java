package hello;

import hello.fetchmodel.A;
import hello.fetchmodel.ARepository;
import hello.fetchmodel.B;
import hello.fetchmodel.BRepository;
import hello.fetchmodel.C;
import hello.fetchmodel.D;
import hello.fetchmodel.DRepository;
import hello.fetchmodel.E;
import hello.fetchmodel.F;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(DRepository dRepository) {
        return (args) -> {
            LOG.info("=======================================================================");
//            int bNumber = 5;
//            int cNumber = 3;
//            Set<B> bs = IntStream.range(0, bNumber).mapToObj(i -> new B(new Long(i),
//                    Integer.toString(i) + "bla",
//                    IntStream.range(0, cNumber)
//                            .mapToObj(j -> new C(new Long(i * cNumber + j),
//                                    Integer.toString(i * cNumber + j))).collect(Collectors.toList())
//                    )).collect(Collectors.toSet());
////            bRepository.saveAll(bs);
//            LOG.info("=======================================================================");
//            A a = new A(1L, bs , "1");
////
//            aRepository.save(a);
////            aRepository.foo();
//            A a1 = aRepository.findById(1L).get();
//            LOG.info("ROFL {}", a1);
            int eNumber = 4;
            int fNumber = 5;
            Set<E> es = LongStream.range(0, eNumber).mapToObj(i -> new E(i)).collect(Collectors.toSet());
            Set<F> fs = LongStream.range(0, fNumber).mapToObj(i -> new F(i)).collect(Collectors.toSet());
            D d = new D(1L, es, fs);
            dRepository.save(d);

            LOG.info("=======================================================================");

            D d1 = dRepository.findById(1L).get();
            LOG.info("ROFL {}", d1);
        };
    }

    private void elementCollection(AuthorRepository authorRepository, AuthorService authorService) throws InterruptedException, java.util.concurrent.ExecutionException {
        init(authorRepository);

        int threadNumber = 5;
        authorService.register(threadNumber);
        CompletableFuture[] futures = IntStream.range(0, threadNumber).mapToObj(i -> {
            return CompletableFuture.runAsync(authorService::foo);
        }).collect(Collectors.toList()).toArray(new CompletableFuture[threadNumber]);

        CompletableFuture.allOf(futures).get();

        Author author = authorRepository.findById(1L).get();
        LOG.info("Retrieved author: {}", author);
    }

    private void init(AuthorRepository authorRepository) {
        Author author = new Author(
                1L,
                "author1",
                IntStream.range(0, 8).mapToObj(Integer::toString).map(Book::new).collect(Collectors.toList())
        );
        authorRepository.save(author);
    }

}