package com.networknt.petstore.fruitHandler;

import com.networknt.body.BodyHandler;
import com.networknt.config.Config;
import com.networknt.handler.LightHttpHandler;
import com.networknt.petstore.model.Fruit;
import com.networknt.petstore.service.FruitBasketService;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.Map;
import java.util.Objects;

public class FruitPostHandler implements LightHttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        System.out.printf("SOUT --> POST HANDLER REACHED");
        httpServerExchange.getResponseHeaders().add(new HttpString("Content-Type"),"application/json");

        Map<String, Object> body = (Map<String, Object>) httpServerExchange.getAttachment(BodyHandler.REQUEST_BODY);
        Fruit myFruit = Config.getInstance().getMapper().convertValue(body, Fruit.class);
        FruitBasketService service = new FruitBasketService();

        //System.out.println("sout fruit " + myFruit.toString());
        service.addFruit(myFruit);

        httpServerExchange.setStatusCode(200);
        httpServerExchange.getResponseSender().send("Added!");

    }
}
