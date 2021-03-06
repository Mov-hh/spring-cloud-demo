package demo.base.controller;

import demo.base.api.BaseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController implements BaseAPI {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Override
    public String hello(@RequestParam("user") String user) {
        logger.debug("Calling base controller. user: {}", user);
        return "hello, " + user;
    }
}
