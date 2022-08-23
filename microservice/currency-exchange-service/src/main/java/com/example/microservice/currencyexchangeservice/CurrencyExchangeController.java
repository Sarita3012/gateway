package com.example.microservice.currencyexchangeservice;

import com.example.microservice.currencyexchangeservice.bean.ExchangeValues;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValues retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        return new ExchangeValues(100L,from,to, BigDecimal.valueOf(65));
    }
}
