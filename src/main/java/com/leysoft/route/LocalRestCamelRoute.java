
package com.leysoft.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.leysoft.exception.CountryException;

@Component
public class LocalRestCamelRoute extends RouteBuilder {

    @Value(
            value = "${route.from.local-rest}")
    private String fromLocalRest;
    
    @Value(
            value = "${route.direct}")
    private String direct;
    
    @Value(
            value = "${route.to.db}")
    private String toDb;

    @Autowired
    @Qualifier(
            value = "countryProcessor")
    private Processor countryProcessor;

    @Autowired
    @Qualifier(
            value = "databaseProcessor")
    private Processor databaseProcessor;
    
    @Autowired
    @Qualifier(
            value = "mailProcessor")
    private Processor mailProcessor;

    @Override
    public void configure() throws Exception {
    	onException(CountryException.class).log(LoggingLevel.ERROR, "Error: CountryException ${body}")
        		.process(mailProcessor);
        restConfiguration().component("jetty").host("localhost").port(8081);
        rest().post(fromLocalRest).to(direct);
        from(direct).routeId("localRestCamelRoute").log("body -> ${body}").convertBodyTo(String.class)
        		.process(countryProcessor).process(databaseProcessor).to(toDb);
    }
}
