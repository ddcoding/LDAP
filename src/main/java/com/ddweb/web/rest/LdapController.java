package com.ddweb.web.rest;

import com.ddweb.model.LdapFilter;
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
    @Autowired
    public LdapController(LdapConnection ldapConnection) {
        this.ldapConnection = ldapConnection;
    }

    /**
     *  @param ldapFilters list of attributes
     *  @return list of filtered records
     */
    @GetMapping("/filters/{ldapFilters}")
    @ResponseBody
    public ResponseEntity<List<String>> getFilters(@PathVariable List<String> ldapFilters) {
        if (!ldapFilters.isEmpty()) {
            List<String> filters = ldapConnection.ConnectViaLdap(ldapFilters);
            return new ResponseEntity<>(filters, HttpStatus.OK);
        } else
            return ResponseEntity.badRequest().build();
    }
}
