package com.ddweb.web.rest;

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
 *  Ldap REST Controller for pulling and pushing records
 */
@RestController
@RequestMapping("/api")
public class LdapController {
    /**
     *  Variable used to get filtered records from LDAP
     */
    private LdapConnection ldapConnection;
    private Interpreter interpreter;
    @Autowired
    public LdapController(LdapConnection ldapConnection, Interpreter interpreter) {
        this.ldapConnection = ldapConnection;
        this.interpreter = interpreter;
    }


    /**
     *  @param ldapFilters list of attributes
     *  @return list of filtered records
     */
    @GetMapping("/filters/{ldapFilters}")
    @ResponseBody
    public ResponseEntity<List<String>> getFilters(@PathVariable List<String> ldapFilters) {
        if (!ldapFilters.isEmpty()) {
            List<String> interpretedList = interpreter.merge(ldapFilters);
            List<String> filters = ldapConnection.ConnectViaLdap(interpretedList);
            return new ResponseEntity<>(filters, HttpStatus.OK);
        } else
            return ResponseEntity.badRequest().build();
    }
}
