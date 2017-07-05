package demo.complex.service;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.base.api.BaseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    private BaseAPI baseAPI;

//    @HystrixCommand(fallbackMethod = "defaultHello")
    public String hello(String user) {
        return baseAPI.hello(user);
    }

    public String defaultHello(String user) {
        return "Error hello: " + user;
    }
}
