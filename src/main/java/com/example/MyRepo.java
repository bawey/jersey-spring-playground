package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Resource
@Slf4j
public class MyRepo {
    public List<String> getStuff() {
        return Stream.of("A", "b", "see").collect(Collectors.toList());
    }

    @PostConstruct
    public void fooBar(){
        log.info("Repository bean created!");
    }
}
