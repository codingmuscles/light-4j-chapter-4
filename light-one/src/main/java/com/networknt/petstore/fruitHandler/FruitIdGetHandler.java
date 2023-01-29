package com.networknt.petstore.fruitHandler;

import com.networknt.handler.LightHttpHandler;
import com.networknt.petstore.model.Fruit;
import com.networknt.petstore.service.FruitBasketService;
import com.networknt.petstore.util.Utility;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class FruitIdGetHandler implements LightHttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        System.out.println("SOUT -> REACHED GET ID HANDLER");
        httpServerExchange.getResponseHeaders().add(new HttpString("Content-Type"),"application/json");

        FruitBasketService service = new FruitBasketService();

        int fruitId = Utility.getParamByHeaderName(httpServerExchange,"fruit_id");
        Fruit myFruit =  service.getFruitById(fruitId);

        if(myFruit == null){
            httpServerExchange.setStatusCode(404); //404=NOT_FOUND
            httpServerExchange.getResponseSender().send("Fruit Not Found!");
        }
        httpServerExchange.setStatusCode(200); //200=OK
        httpServerExchange.getResponseSender().send(Utility.getJsonStringFromObject(myFruit));

    }
}
