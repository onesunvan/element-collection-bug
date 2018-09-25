package hello;

import hello.fetchmodel.A;
import hello.fetchmodel.ARepository;
import hello.fetchmodel.B;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(ARepository aRepository) {
        return (args) -> {
            LOG.info("=======================================================================");
//            List<B> bs = IntStream.range(0, 5).mapToObj(i -> new B(new Long(i), Integer.toString(i))).collect(Collectors.toList());
//            A a = new A(1L, null , "1");
//
//            aRepository.save(a);
            aRepository.foo();
            A a = aRepository.findById(1L).get();
            LOG.info("ROFL {}", a);
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