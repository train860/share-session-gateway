package com.ttt.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.netty.http.server.HttpServerRequest;

@RestController

public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "hello world";
    }
}
