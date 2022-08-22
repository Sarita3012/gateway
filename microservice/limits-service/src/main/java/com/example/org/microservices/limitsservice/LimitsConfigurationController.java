package com.example.org.microservices.limitsservice;

import com.example.org.microservices.limitsservice.bean.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitFromConfiguration(){

        return new LimitConfiguration(configuration.getMinimum(),configuration.getMaximum());
    }
}
