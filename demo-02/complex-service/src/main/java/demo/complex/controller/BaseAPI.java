package demo.complex.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "base-service", fallback = BaseAPI.BaseAPIFallback.class)
public interface BaseAPI {
    @GetMapping("/hello")
    String hello(@RequestParam("user") String user);

    @Component
    class BaseAPIFallback implements BaseAPI {

        @Override
        public String hello(String user) {
            return "I am sorry";
        }
    }
}
