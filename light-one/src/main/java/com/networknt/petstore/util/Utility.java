package com.networknt.petstore.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utility {


    public static String getJsonStringFromObject(Object object) {
        String result = "";
        try {
            ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
            result = writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result ;
    }

    public static int getParamByHeaderName(HttpServerExchange exchange, String input) {
        Map<String, String> params = new HashMap<>();
        Map<String, Deque<String>> pnames = exchange.getQueryParameters();
        for(Map.Entry<String, Deque<String>> entry: pnames.entrySet()){
            String pname = entry.getKey();
            Iterator<String> pvalues = entry.getValue().iterator();
            if(pvalues.hasNext()){
                params.put(pname,pvalues.next());
            }
        }

        String value = params.get(input);
        return Integer.valueOf(value);
    }
}
