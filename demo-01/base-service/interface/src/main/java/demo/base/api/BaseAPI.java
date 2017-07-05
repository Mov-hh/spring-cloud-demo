package demo.base.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 【注意】切忌同时使用 @FeignClient + @RequestMapping，否则
 *
 * <p>1. 使用`fallback(服务降级)`机制时会很纠结、抓狂</p>
 * <p>2. 当调用方也存在相同URI的路由方法时会报异常</p>
 */
@FeignClient("base-service")
public interface BaseAPI {
    /**
     * 【注意】
     * <p>1. 必须是`@RequestParam("user")`，不能是`@RequestParam`，否则调用方启动时会报异常</p>
     * <p>2. Feign新版本已支持使用@GetMapping/@PostMapping等注解。PS: 具体从哪个版本开始支持未深入深究</p>
     * <p>3. 不要为@RequestMapping注解的value属性赋值数组，否则调用方会报异常。如果有同一方法映射多个路由的需求，可以在对应的
     * Controller方法上添加</p>
     * @param user
     * @return
     */
    // @GetMapping({"hello", "world"}) //  服务调用方应用启动时会报：java.lang.IllegalStateException: Method hello can only contain at most 1 value field. Found: [hello, world]
    @GetMapping("hello")
    // String hello(@RequestParam String user); // 服务调用方应用启动时会报：java.lang.IllegalStateException: RequestParam.value() was empty on parameter 0
    String hello(@RequestParam("user") String user);
}
