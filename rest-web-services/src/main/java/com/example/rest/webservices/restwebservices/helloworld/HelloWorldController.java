package com.example.rest.webservices.restwebservices.helloworld;

import com.example.rest.webservices.restwebservices.helloworld.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path="/hello-world")
    public String helloWorld(){
        return "Hello World!!!";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World , %s",name));
    }

    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized(
            @RequestHeader(name = "Accept-Language",required = false) Locale locale
    ){
        //When we use LocaleContextHolder.getLocale() insteadof locale object
        // that time no need to tick Accept-language as key in postman
        return messageSource.getMessage("good.morning.message",null,"Default Message", LocaleContextHolder.getLocale());

        //return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
        //return "Hello World!!!";
    }

}
