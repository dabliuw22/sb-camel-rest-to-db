
package com.leysoft.route;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

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
        restConfiguration().component("restlet").port(8080);
        rest().post("/country").to("direct:hello");
        from("direct:hello").log("body -> ${body}").routeId("localRestCamelRoute");
    }
}
