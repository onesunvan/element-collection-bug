package hello;

import nfs.NfsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


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
            System.out.println(nfsService.validateNfs(args[0], "/var/nfs"));
            System.out.println(nfsService.doesFileExist(args[0], "/var/nfs", "/dummy"));
            System.out.println(nfsService.getFileSize(args[0], "/var/nfs", "/dummy"));
            System.out.println(nfsService.getFileSize(args[0], "/var/nfs", "/DVB-S_EuroNews.ts"));
            System.out.println(nfsService.doesFileExist(args[0], "/var/nfs", "/dummy1112222"));

            CompletableFuture.runAsync(() -> {
                nfsService.downloadFile(args[0], "/var/nfs", "/BBC1_1.ts", "asdfdsdf/BBC1_1.ts");
            });
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(progressRunable(nfsService, args[0], "/var/nfs", "/BBC1_1.ts", "asdfdsdf/BBC1_1.ts"),
                    5, 10, TimeUnit.SECONDS);
        };
    }

    private Runnable progressRunable(NfsService nfsService, String ip, String sharedPath, String filePath, String targetFilePath) {
        return () -> {
            long fullFileSize = nfsService.getFileSize(ip, sharedPath, filePath);
            long currentFileSize = new File(targetFilePath).length();
            LOG.info("current file size - {} from {}. {} %", currentFileSize, fullFileSize, (double) currentFileSize / fullFileSize * 100);
        };
    }
}