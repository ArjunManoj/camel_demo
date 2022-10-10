package com.dev.camelmicorservicea.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class FirstRoute extends RouteBuilder {
    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent loggingComponent;

    @Override
    public void configure() throws Exception {
        from("timer: first-timer?period=10000")
                .log("${body}")//null
                .transform().constant("My Constant Message")
                .log("${body}")//My Constant Message
                .bean(getCurrentTimeBean)
                .log("${body}")//Time now is2021-01-18T18:32:19.660244
                .bean(loggingComponent)
                .log("${body}")
                .process(new SimpleLoggingProcessor())
                .to("log: logger");
    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is" + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent {

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    public void process(String message) {

        logger.info("SimpleLoggingProcessingComponent {}", message);

    }
}

class SimpleLoggingProcessor implements Processor {

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }

}
