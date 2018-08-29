
package com.leysoft.route;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalRestCamelRoute extends RouteBuilder {

    @Value(
            value = "${route.from.local-rest}")
    private String fromLocalRest;

    @Autowired
    @Qualifier(
            value = "countryProcessor")
    private Processor countryProcessor;

    @Autowired
    @Qualifier(
            value = "databaseProcessor")
    private Processor databaseProcessor;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("jetty").host("localhost").port("8080")
                .bindingMode(RestBindingMode.json);
        rest().post("/country").to("direct:hello");
        from("direct:hello").log("body -> ${body}").routeId("localRestCamelRoute");
    }
}
