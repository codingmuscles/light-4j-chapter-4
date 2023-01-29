package com.networknt.petstore.fruitHandler;

import com.networknt.body.BodyHandler;
import com.networknt.config.Config;
import com.networknt.handler.LightHttpHandler;
import com.networknt.petstore.model.Fruit;
import com.networknt.petstore.service.FruitBasketService;
import com.networknt.petstore.util.Utility;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.Map;

public class FruitIdPutHandler implements LightHttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        System.out.println("SOUT -> REACHED PUT ID HANDLER");
        httpServerExchange.getResponseHeaders().add(new HttpString("Content-Type"),"application/json");

        FruitBasketService service = new FruitBasketService();

        //getting path variable
        int fruitId = Utility.getParamByHeaderName(httpServerExchange,"fruit_id");
        Fruit myFruit =  service.getFruitById(fruitId);

        //getting payload
        Map<String, Object> body = (Map<String, Object>) httpServerExchange.getAttachment(BodyHandler.REQUEST_BODY);
        Fruit payloadFruit = Config.getInstance().getMapper().convertValue(body, Fruit.class);

        if(myFruit != null){
            //logic for update
            httpServerExchange.setStatusCode(200);
            httpServerExchange.getResponseSender().send(Utility.getJsonStringFromObject(service.updateFruitById(fruitId, payloadFruit)));
        } else {
            httpServerExchange.setStatusCode(404);
            httpServerExchange.getResponseSender().send("sout --> Fruit not found for id " + fruitId);
        }

    }
}
