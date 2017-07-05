package demo.base.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "base-service", fallback = BaseAPIFallback.class)
//@FeignClient(value = "base-service")
public interface BaseAPI {
    @GetMapping("/hello")
    String hello(@RequestParam("user") String user);
}
