package com.dev.camelmicorserviceb.route;

import com.dev.camelmicorserviceb.model.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from("activemq:my-activemq-queue1")
//                .to("log:log-messages-from-activemq");

        from("activemq:my-activemq-queue1")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .to("log:log-messages-from-activemq");
    }
}
