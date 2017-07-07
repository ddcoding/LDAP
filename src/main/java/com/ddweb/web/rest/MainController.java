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

@RestController
@RequestMapping("/api")
public class MainController {

    private LdapConnection ldapConnection;
    @Autowired
    public MainController(LdapConnection ldapConnection) {
        this.ldapConnection = ldapConnection;
    }

    @GetMapping("/filters/{ldapFilters}")
    @ResponseBody
    public void getFilters(@PathVariable String[] ldapFilters){
        System.out.print(ldapFilters[0]);
        System.out.print(ldapFilters[1]);
//        List<LdapFilter> ldapFilters = new ArrayList<>();
//        Collections.addAll(ldapFilters, ldapFiltersArray);
//        if(!ldapFilters.isEmpty()) {
//            List<String> filters = ldapConnection.ConnectViaLdap(ldapFilters);
//            return new LdapFilter("lol","lel");
//        }else
//            return ResponseEntity.badRequest().build();
    }
}
