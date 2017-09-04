package com.ug369.backend.outerapi.annotation;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryCache {

    private static ConcurrentHashMap<String,String> cache = new ConcurrentHashMap<String,String>();

    public void put(String key,String value){
        cache.put(key, value);
    }

    public String get(String key){
        return cache.get(key);
    }
}
