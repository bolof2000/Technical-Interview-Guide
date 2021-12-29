package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class DesignQuestions {

    Map<Integer,Integer> map = new HashMap<>();
    Deque<Integer> deque = new ArrayDeque<>();
    private final int capacity;

    public DesignQuestions(int capacity){
        this.capacity = capacity;
    }

    public int getDataFromCache(int key){
        if(this.map.containsKey(key)){
           int val = map.get(key);
           this.deque.addFirst(key);

           return val;
        }
        return -1;
    }

    public void put(int value,int key){
        //todo check if the key is the map- remove the key from deque and the map  then add the key back to the front of the queue since its now most recently

        //todo if the key is not in the map, check the size of the cache,and then update

        if(!this.map.containsKey(key)){
            if(this.capacity == map.size()){
                int oldest = this.deque.removeLast();
                this.map.remove(oldest);
            }
            this.deque.addFirst(key);
            this.map.put(key,value);
        }
    }
}
