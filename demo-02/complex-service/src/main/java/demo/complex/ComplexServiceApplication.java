package demo.complex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("demo.base.api")
@EnableHystrix
public class ComplexServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComplexServiceApplication.class, args);
    }
}
