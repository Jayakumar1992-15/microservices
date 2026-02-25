package com.in28minutes.microservices.currency_conversion_service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
    
    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {
        // return new CurrencyConversion(
        //         1000L,from,to,
        //         quantity,BigDecimal.ONE,BigDecimal.ONE,"");

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        CurrencyConversion response = new RestTemplate().getForObject(
        "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
        CurrencyConversion.class,
        uriVariables);

        if (response == null) {
        return null;
        }

        return new CurrencyConversion(
        response.getId(),
        from,
        to,
        response.getConversionMultiple(),
        quantity,
        quantity.multiply(response.getConversionMultiple()),
        response.getEnvironment()+" "+"rest template");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        CurrencyConversion response = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(
                response.getId(),
                from,
                to,
                response.getConversionMultiple(),
                quantity,
                quantity.multiply(response.getConversionMultiple()),
                response.getEnvironment() + " feign");
    }
}