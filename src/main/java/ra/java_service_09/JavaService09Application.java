package ra.java_service_09;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class JavaService09Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaService09Application.class, args);
    }
    @Bean
    public CommandLineRunner run(LoggingExample logger) {
        return args -> logger.runLoggingDemo();
    }
}
