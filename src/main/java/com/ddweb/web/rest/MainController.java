package com.ddweb.web.rest;

import com.ddweb.model.LdapFilter;
import com.ddweb.service.LdapConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private LdapConnection ldapConnection;
    @Autowired
    public MainController(LdapConnection ldapConnection) {
        this.ldapConnection = ldapConnection;
    }

    @GetMapping("/filters")
    public ResponseEntity<List<String>> getFilters(@RequestBody List<LdapFilter> ldapFilters){
        if(ldapFilters!=null) {
            List<String> filters = ldapConnection.ConnectViaLdap(ldapFilters);
            return new ResponseEntity<>(filters, HttpStatus.OK);
        }else
            return ResponseEntity.badRequest().build();
    }
}
