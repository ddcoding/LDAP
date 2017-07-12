package com.ddweb.web.rest.security;

import com.ddweb.enums.ConvertType;
import com.ddweb.model.User;
import com.ddweb.service.ldap.ContactAttrJSON;
import com.ddweb.service.ldap.LdapConnection;
import com.ddweb.service.ldap.LdapLogged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private LdapLogged ldapLogged;

    private final LdapConnection ldapConnection;

    @Autowired
    public AuthController(LdapLogged ldapLogged, LdapConnection ldapConnection) {
        this.ldapLogged = ldapLogged;
        this.ldapConnection = ldapConnection;
    }

    @GetMapping("/islogged")
    @ResponseBody
    public ResponseEntity<List<String>> isLogged(){
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
        {
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            AndFilter andFilter = new AndFilter();
            String[] splitted = userName.split("@");
            andFilter.and(new EqualsFilter("sAMAccountName",splitted[0]));
            List<Object> joList = ldapLogged.getLdapTemplate().search("", andFilter.encode(), new ContactAttrJSON());
            List<String> stringList;
            stringList = ldapConnection.convert(joList, ConvertType.NAMES);
            return new ResponseEntity<>(stringList, HttpStatus.ACCEPTED);
        }
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/login/{userName}/{password}")
    @ResponseBody
    public ResponseEntity logIn(@PathVariable("userName") String userName, @PathVariable("password") String password){

        if(ldapLogged.isLogged(userName, password)) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null, AuthorityUtils.createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/logoutinfo")
    @ResponseBody
    public String logOutInfo(){
        return "wylogowano pomyslnie!";
    }
}
