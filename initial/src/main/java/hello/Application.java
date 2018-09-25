package hello;

import antlr.collections.impl.IntRange;
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
    public CommandLineRunner demo(AuthorRepository authorRepository, AuthorService authorService) {
        return (args) -> {
            init(authorRepository);

            int threadNumber = 2;
            authorService.register(threadNumber);
            CompletableFuture[] futures = IntStream.range(0, threadNumber).mapToObj(i -> {
                return CompletableFuture.runAsync(authorService::foo);
            }).collect(Collectors.toList()).toArray(new CompletableFuture[threadNumber]);

            CompletableFuture.allOf(futures).get();

            Author author = authorRepository.findById(1L).get();
            LOG.info("Retrieved author: {}", author);
        };
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