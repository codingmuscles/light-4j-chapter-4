package com.networknt.petstore.fruitHandler;

import com.networknt.handler.LightHttpHandler;
import com.networknt.petstore.model.Fruit;
import com.networknt.petstore.service.FruitBasketService;
import com.networknt.petstore.util.Utility;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.ArrayList;
import java.util.List;

public class FruitsGetHandler implements LightHttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        System.out.println("SOUT -> GET HANDLER REACHED");

        //1. set the content type of response
        httpServerExchange.getResponseHeaders().add(new HttpString("Content-Type"),"application/json");

        //2. collect the response
        FruitBasketService service = new FruitBasketService();


        //3. send back the response (i.e. status code and data)
        httpServerExchange.setStatusCode(200); //200=OK
        httpServerExchange.getResponseSender().send(Utility.getJsonStringFromObject(service.getFruits()));
    }
}
