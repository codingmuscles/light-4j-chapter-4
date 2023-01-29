package com.networknt.petstore.fruitHandler;

import com.networknt.handler.LightHttpHandler;
import com.networknt.petstore.model.Fruit;
import com.networknt.petstore.service.FruitBasketService;
import com.networknt.petstore.util.Utility;
import io.undertow.server.HttpServerExchange;

public class FruitIdDeleteHandler implements LightHttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        System.out.println("SOUT --> DELETE HANDLER REACHED");

        FruitBasketService service = new FruitBasketService();

        int fruitId = Utility.getParamByHeaderName(httpServerExchange,"fruit_id");
        boolean resultBool = service.deleteFruitById(fruitId);

        if(resultBool){
            httpServerExchange.setStatusCode(200); // OK deleted
            httpServerExchange.getResponseSender().send("Deleted Fruit with id: " + fruitId);
        }
        httpServerExchange.setStatusCode(404); // OK deleted
        httpServerExchange.getResponseSender().send("No Fruit foound with id: " + fruitId);


    }
}
