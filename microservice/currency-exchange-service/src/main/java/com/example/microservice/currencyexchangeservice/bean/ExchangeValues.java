package com.example.microservice.currencyexchangeservice.bean;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExchangeValues {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;

    public ExchangeValues() {
    }

    public ExchangeValues(Long id, String from, String to, BigDecimal conversionMultiple) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }
}
