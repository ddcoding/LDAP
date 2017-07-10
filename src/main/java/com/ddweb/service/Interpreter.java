package com.ddweb.service;


import com.ddweb.structures.Pair;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Interpreter class for replace values from client side to pair which contains key,value. It's necessary to communicate with LDAP repository
 */
@Service
public class Interpreter {

    private static final String GROUP_ADMIN = "ou=admin";

    private static final String GROUP_USER = "ou=user";

    private static final String GROUP_MOD = "ou=mod";

    private static final String GROUP_SUPPORT = "ou=Support";

    public Interpreter() {
    }

    private Environment env;

    @Autowired
    public Interpreter(Environment env)
    {
        this.env = env;
    }

    /**
     * @param value - type of record from LDAP
     * @return pair which contains value,key for LDAP communication
     * @see Pair
     */
    public Pair<String,String> interpret(String value) {
        if(value!=null)
            switch (value) {
                case "admin":
                    return new Pair<>("ou",Interpreter.GROUP_ADMIN + "," + Interpreter.GROUP_SUPPORT + "," + env.getProperty("LdapBase"));
                case "user":
                    return new Pair<>("ou",Interpreter.GROUP_USER + "," + Interpreter.GROUP_SUPPORT + "," + env.getProperty("LdapBase"));
                case "mod":
                    return new Pair<>("ou",Interpreter.GROUP_MOD + "," + Interpreter.GROUP_SUPPORT + "," + env.getProperty("LdapBase"));
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
            Pair<String,String> pair;
            List<String> newList = new ArrayList<>();
            for (String s : valuesList) {
                pair = interpret(s);
                if (pair != null) {
                    newList.add(pair.getK());
                    newList.add(pair.getL());
                }
            }
            return newList;
        } else return null;
    }
}
