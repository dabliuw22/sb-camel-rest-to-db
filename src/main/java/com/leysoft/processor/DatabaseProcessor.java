
package com.leysoft.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(
        value = "databaseProcessor")
public class DatabaseProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseProcessor.class);

    private static final String INSERT_FORMAT = "insert into countries(value) values(':value')";

    @Override
    public void process(Exchange exchange) throws Exception {
        String country = (String) exchange.getIn().getBody();
        String insertCountry = INSERT_FORMAT.replace(":value", country);
        LOGGER.info("body -> {}", insertCountry);
        exchange.getIn().setBody(insertCountry);
    }
}
