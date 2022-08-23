package com.example.microservice.eurekaserver.EurekaClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/list")
    public List<String> getProductList(){
        List<String> list =new ArrayList<>();
            list.add("laptop");
            list.add("mobile");
               return list;
            }
}
