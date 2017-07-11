package com.ddweb.web.rest;

import com.ddweb.annotations.DevProfile;
import com.ddweb.enums.ConvertType;
import com.ddweb.model.LdapFilter;
import com.ddweb.service.Interpreter;
import com.ddweb.service.LdapConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Ldap REST Controller for pulling and pushing records
 */
@RestController
@RequestMapping("/api/ldap")
public class LdapController {
    /**
     * Variable used to get filtered records from LDAP
     */
    private LdapConnection ldapConnection;
    private Interpreter interpreter;

    @Autowired
    public LdapController(LdapConnection ldapConnection, Interpreter interpreter) {
        this.ldapConnection = ldapConnection;
        this.interpreter = interpreter;
    }


    /**
     * It's <b>GET</b> method for download list of <b>names and surnames</b> as results after filtering data from client side.
     * @param ldapFilters list of attributes
     * @return status 200 and list of filtered records downloaded from LDAP repository or status 400 when param data is wrong or something else is wrong
     */
    @GetMapping("/filters/{ldapFilters}")
    @ResponseBody
    public ResponseEntity<List<String>> getFilters(@PathVariable List<String> ldapFilters) {
        if (!ldapFilters.isEmpty()) {
            List<String> interpretedList = interpreter.merge(ldapFilters);
            if (!interpretedList.isEmpty()) {
                List<String> filters = ldapConnection.connectViaLdap(interpretedList, ConvertType.NAMES);
                return new ResponseEntity<>(filters, HttpStatus.OK);
            } else {
                System.err.print("BLEDNE DANE !");
                return ResponseEntity.badRequest().build();
            }
        } else
            return ResponseEntity.badRequest().build();
    }

    /**
     * It's <b>GET</b> method for download list of <b>GROUPS</b> from client side.
     * @return status 200 and list of groups downloaded from LDAP repository or status 400 when something is wrong
     */
    @GetMapping("/groups")
    @ResponseBody
    public ResponseEntity<List<String>> getRoles() {
        List<String> filters = new ArrayList<>();
        filters.add("objectclass");
        filters.add("group");
        filters = ldapConnection.connectViaLdap(filters,ConvertType.GROUPS);
        if(filters != null) return new ResponseEntity<>(filters,HttpStatus.OK);
        else return ResponseEntity.badRequest().build();
    }
}
