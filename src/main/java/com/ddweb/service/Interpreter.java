package com.ddweb.service;


import com.ddweb.structures.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Interpreter class for replace values from client side to pair which contains key,value. It's necessary to communicate with LDAP repository
 */
@Component
public class Interpreter {

    public Interpreter() {
    }

    /**
     * @param value - type of record from LDAP
     * @return key for LDAP communication
     */
    public String interpret(String value) {
        if(value!=null)
        switch (value) {
            case "admin":
                return "ou";
            case "user":
                return "ou";
            case "mod":
                return "ou";
            default:
                return null;
        }else return null;
    }

    /**
     * That method is merging values with keys using interpret method.
     *
     * @param valuesList - List of values of records from Angular used for search, which is merged with attributes
     * @return newList which contains pairs of key and value. In first index of list, we have key and in the second we have value. newList[0] = key , newList[1] = value
     */
    public List<String> merge(List<String> valuesList) {
        if (valuesList != null) {

            List<String> newList = new ArrayList<>();
            String inter;
            for (String s : valuesList) {
                inter = interpret(s);
                if (inter != null) {
                    newList.add(inter);
                    newList.add(s);
                }
            }
            return newList;
        } else return null;
    }
}
