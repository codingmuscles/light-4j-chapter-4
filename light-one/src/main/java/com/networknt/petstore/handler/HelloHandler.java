package com.networknt.petstore.handler;

import com.networknt.handler.LightHttpHandler;
import io.undertow.server.HttpServerExchange;

public class HelloHandler implements LightHttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {

        httpServerExchange.setStatusCode(200); // OK
        httpServerExchange.getResponseSender().send("Hello World!");

    }
}
