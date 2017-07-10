package com.ddweb.service;


import com.ddweb.structures.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Interpreter {

    public String interpret(String s) {
        switch (s) {
            case "admin":
                return "ou";
            case "user":
                return "ou";
            case "mod":
                return "ou";
            default:
                return null;
        }
    }

    public List<String> merge(List<String> strings){
        List<String> newList = new ArrayList<>();
        String inter;
        for(String s:strings){
            inter = interpret(s);
            if(inter!=null) {
                newList.add(inter);
                newList.add(s);
            }
        }
        return newList;
    }
}
