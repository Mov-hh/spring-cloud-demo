package demo.base.controller;

import demo.base.api.BaseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 【注意】一定不要在 Controller 类上使用类似`@RequestMapping(value = "/base", produces = MediaType.APPLICATION_JSON_VALUE)`
 * 这样的注解值，这会改变 BaseAPI 接口的路由规则，当使用Feign调用服务时会报找不到对应服务异常。一般情况下可以考虑不加@RequestMapping
 * 注解，如果必须使用@RequestMapping注解，可以考虑不填充注解的value属性
 */
@RestController
public class BaseController implements BaseAPI {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 【提示】该方法可以从接口方法继承 @GetMapping/@RequestMapping 等注解，但无法继承方法参数中的
     * `@RequestParam`/`@PathVariable`/`@RequestBody` 等注解。因此，仍需用相应注解修饰对应方法参数。
     *
     * <p>PS: 此处`@RequestParam("user")`、`@RequestParam`均可，既不指定`value`属性亦可。但是，这个方法参数一般会直接从接口中复制、
     * 粘贴而来，因此会保留下来`value`属性</p>
     *
     * @param user
     * @return
     */
    @Override
//    @GetMapping({"hello", "world"}) // 可以这样，只要保留`hello`即可
    public String hello(@RequestParam("user") String user) {
        logger.debug("Calling base controller. user: {}", user);
        return "hello, " + user;
    }
}
