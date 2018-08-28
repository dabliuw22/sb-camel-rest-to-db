package com.leysoft.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

@Component
public class RestCamelRoute extends RouteBuilder {
	
	@Value(
            value = "${route.from.timer}")
    private String fromTimer;

    @Value(
            value = "${route.to.rest}")
    private String toRest;
    
    @Value(
            value = "${route.to.local-rest}")
    private String toLocalRest;
    
    @Autowired
    @Qualifier(value = "mailProcessor")
    private Processor mailProcessor;

	@Override
	public void configure() throws Exception {
		onException(Exception.class).log(LoggingLevel.ERROR, "Error: PersonException ${body}")
        .process(mailProcessor);
		onException(MailException.class).log(LoggingLevel.ERROR, "Error: MailException ${body}");
		from(fromTimer).routeId("restCamelRoute")
			.setHeader(Exchange.HTTP_METHOD, constant("GET"))
        	.to(toRest).convertBodyTo(String.class).log("body -> ${body}")
        	.setHeader(Exchange.HTTP_METHOD, constant("POST"))
        	.to(toLocalRest).log("body -> ${body}");
	}
}
