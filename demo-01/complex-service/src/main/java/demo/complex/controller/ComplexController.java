package demo.complex.controller;

import demo.base.api.BaseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplexController {
    private static Logger logger = LoggerFactory.getLogger(ComplexController.class);

    @Autowired
    private BaseAPI baseAPI;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam String user) {
        logger.debug("Calling complex service. user: {}", user);
        return baseAPI.hello(user);
    }
}
