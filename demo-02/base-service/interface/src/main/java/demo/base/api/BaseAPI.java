package demo.base.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("base-service")
@RequestMapping(value = "/base", produces = MediaType.APPLICATION_JSON_VALUE)
public interface BaseAPI {
    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    String hello(@RequestParam("user") String user);
}
