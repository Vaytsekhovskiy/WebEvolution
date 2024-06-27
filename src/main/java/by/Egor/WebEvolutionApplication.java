package by.Egor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"by.egor.inmemorybroker", "by.Egor"})
public class WebEvolutionApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebEvolutionApplication.class, args);
    }
}
