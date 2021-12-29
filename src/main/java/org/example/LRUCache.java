package org.example;

import java.util.Deque;
import java.util.Map;

public class LRUCache {

    private Map<Integer,Integer> map;
    Deque<Integer> deque;
    private final int capacity;

    public LRUCache(int capacity){
        this.capacity = capacity;
    }

    public int getValue(int key){

        if(map.containsKey(key)){
            int val = map.get(key);

            this.deque.addFirst(key);

            return val;
        }
        return -1;
    }

    public void put(int key,int val){
        if(!map.containsKey(key)){
            if(this.capacity == map.size()){
                int oldest = this.deque.removeLast();
                map.remove(oldest);
            }else{

                this.deque.remove(key);

            }
            this.deque.addFirst(key);
            this.map.put(key,val);
        }
    }

}
