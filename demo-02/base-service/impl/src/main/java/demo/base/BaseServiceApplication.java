package demo.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseServiceApplication.class, args);
    }
}
