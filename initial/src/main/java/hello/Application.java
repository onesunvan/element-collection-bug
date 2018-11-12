package hello;

import nfs.NfsService;
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
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public NfsService nfsService() {
        return new NfsService();
    }

    @Bean
    public CommandLineRunner demo(NfsService nfsService) {
        return (args) -> {
            LOG.info("Starting sample app");
            nfsService.init(args[0]);
            nfsService.foo();
        };
    }
}