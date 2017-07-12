package com.ddweb.web.rest.security;

import com.ddweb.model.User;
import com.ddweb.service.ldap.LdapLogged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private LdapLogged ldapLogged;

    @Autowired
    public AuthController(LdapLogged ldapLogged) {
        this.ldapLogged = ldapLogged;
    }

    @GetMapping("/islogged")
    @ResponseBody
    public String isLogged(){
        return "Zalogowano mnie!";
    }

    @PostMapping("/login")
    @ResponseBody
    public String logIn(@RequestBody User user){

        if(ldapLogged.isLogged(user.getUserName(), user.getPassword())) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, AuthorityUtils.createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "Zalogowano!";
        }
        else
            return "BLEDNE DANE LOGOWANIA ! NIE UDALO SIE !!!! JESTES SLABY !";
    }

    @GetMapping("/logoutinfo")
    @ResponseBody
    public String logOutInfo(){
        return "wylogowano pomyslnie!";
    }
}
