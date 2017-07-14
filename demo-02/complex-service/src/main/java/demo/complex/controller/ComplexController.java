package demo.complex.controller;

//import demo.complex.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplexController {
    private static Logger logger = LoggerFactory.getLogger(ComplexController.class);

//    @Autowired
//    private BaseService baseService;

    @Autowired
    private BaseAPI baseAPI;

//    @GetMapping("/hello")
//    public String hello(@RequestParam("user") String user) {
//        logger.debug("Calling complex service hello. user: {}", user);
//        return baseService.hello(user);
//    }

    @GetMapping("/hello2")
    public String hello2(@RequestParam("user") String user) {
        logger.debug("Calling complex service hello2. user: {}", user);
        return baseAPI.hello(user);
    }

}
