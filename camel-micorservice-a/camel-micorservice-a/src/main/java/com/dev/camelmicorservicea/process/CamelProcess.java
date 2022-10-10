package com.dev.camelmicorservicea.process;

import org.apache.camel.CamelException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CamelProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("exception in processor");
        throw new CamelException();
    }
}
