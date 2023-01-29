package com.networknt.petstore.service;

import com.networknt.petstore.model.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitBasketService {

    private static List<Fruit> fruits ;

    public List<Fruit> getFruits(){
        if(fruits==null){
            fruits = new ArrayList<>();
            fruits.add(new Fruit(1,"Apple",10));
            fruits.add(new Fruit(2,"Banana",15));
            System.out.printf("sout --> creaing list of fresh fruits");
            return fruits;
        }
        System.out.println("sout --> returning existing fruits");
        return fruits;
    }

    public Fruit getFruitById(int id){
        if(fruits !=null){
            for(Fruit fruit : fruits){
                if(id == fruit.getId()){
                    return fruit;
                }
            }
        }
        return null;
    }

    public String addFruit(Fruit fruit){
        fruits.add(fruit);
        return "Added!";
    }


    public Fruit updateFruitById(int fruitId, Fruit payloadFruit) {
        for(Fruit fruit : fruits){
            if(fruit.getId() == fruitId){
                fruit.setWeight(payloadFruit.getWeight());
                fruit.setName(payloadFruit.getName());
                return fruit;
            }
        }
        return null;
    }

    public boolean deleteFruitById(int id){
        Fruit tempFruit = getFruitById(id);
        if(tempFruit != null) {
            fruits.remove(tempFruit);
            return true;
        }
        return false;
    }
}
