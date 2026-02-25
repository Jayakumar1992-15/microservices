package com.in28minutes.microservices.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// ...existing code...
@FeignClient(name = "currency-exchange", url = "http://localhost:8000")
//@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    // ...existing code...

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from,
                                             @PathVariable("to") String to);
    // @GetMapping("/currency-exchange/from/{from}/to/{to}/quantity/{quantity}")
    // public CurrencyConversion retrieveExchangeValue(
    //     @PathVariable String from, 
    //     @PathVariable String to,
    //     @PathVariable BigDecimal quantity)
}