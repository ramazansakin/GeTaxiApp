package com.rsakin.gatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodController {

    @GetMapping("/userServiceFallback")
    public String userServiceFallback(){
        return "Service is not available now. Please try again later.";
    }

}
