package com.ddweb.service;


import com.ddweb.structures.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Interpreter {

    public Pair<String,String> interpret(String s) {
        switch (s) {
            case "admin":
                return new Pair<>("ou", "admin");
            case "user":
                return new Pair<>("ou", "user");
            case "mod":
                return new Pair<>("ou", "mod");
            default:
                return null;
        }
    }

    public List<String> merge(List<String> strings){
        List<String> newList = new ArrayList<>();
        Pair<String,String> pair;
        for(String s:strings){
            pair = interpret(s);
            if(pair!=null) {
                newList.add(pair.getK());
                newList.add(pair.getL());
            }
        }
        return newList;
    }
}
