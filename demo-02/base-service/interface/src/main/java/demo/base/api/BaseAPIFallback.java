package demo.base.api;

import org.springframework.stereotype.Component;

@Component
public class BaseAPIFallback implements BaseAPI {
    @Override
    public String hello(String user) {
        return "I am sorry: " + user;
    }
}
