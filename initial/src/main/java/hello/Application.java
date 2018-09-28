package hello;

import hello.entities.A;
import hello.repositories.ARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(ARepository aRepository) {
        return (args) -> {
            LOG.info("Starting sample app");
            aRepository.save(new A("a1"));
            aRepository.save(new A("a2"));
            aRepository.save(new A("a3"));
            aRepository.save(new A("a4"));
        };
    }
}