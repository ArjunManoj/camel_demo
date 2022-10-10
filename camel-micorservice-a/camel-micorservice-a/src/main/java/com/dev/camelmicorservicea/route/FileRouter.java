package com.dev.camelmicorservicea.route;

import com.dev.camelmicorservicea.handler.CamelException;
import com.dev.camelmicorservicea.process.CamelProcess;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(CamelException.class).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                System.out.println("exception got handled");
            }
        }).log("completed exception");
        from("file:files/input?noop=true")
//                .doTry()
                .process(new CamelProcess())
                .to("file:files/output");
//                .doCatch(CamelException.class).log("${body}");
    }
}
