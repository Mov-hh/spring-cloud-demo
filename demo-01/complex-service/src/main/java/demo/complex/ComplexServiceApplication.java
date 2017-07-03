package demo.complex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("demo.base.api")
public class ComplexServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComplexServiceApplication.class, args);
    }
}
