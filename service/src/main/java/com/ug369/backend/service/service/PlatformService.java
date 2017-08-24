package com.ug369.backend.service.service;

import com.ug369.backend.service.entity.mysql.Platform;
import com.ug369.backend.service.repository.mysql.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roy on 2017/8/24.
 */
@Service
public class PlatformService {


    @Autowired
    private PlatformRepository repository;

    public List<Map<String,Object>> getPlatforms(){
        Iterable<Platform> all = repository.findAll();

        List platforms = new ArrayList<>();
        all.forEach(o->{
            Map<String,Object> entry = new HashMap<>();
            entry.put("value",o.getId());
            entry.put("text",o.getName());
            platforms.add(entry);
        });

        return platforms;
    }
}
