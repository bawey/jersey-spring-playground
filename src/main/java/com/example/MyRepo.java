package com.example;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@Repository
public class MyRepo {
    public List<String> getStuff() {
        return Stream.of("A", "b", "see").collect(Collectors.toList());
    }
}
