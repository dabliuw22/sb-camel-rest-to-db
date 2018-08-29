
package com.leysoft.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.leysoft.exception.CountryException;

@Component(
        value = "countryProcessor")
public class CountryProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String country = exchange.getIn().getBody(String.class);
        if (country == null || country.isEmpty()) {
            throw new CountryException("Validation error");
        }
    }

}
