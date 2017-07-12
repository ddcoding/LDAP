package com.ddweb.web.rest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/islogged")
    @ResponseBody
    public String isLogged(){
        return "Zalogowano mnie!";
    }

    @GetMapping("/login")
    @ResponseBody
    public String logIn(){


        Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, AuthorityUtils.createAuthorityList("ROLE_USER"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Zalogowano!";
    }

    @GetMapping("/logoutinfo")
    @ResponseBody
    public String logOutInfo(){
        return "wylogowano pomyslnie!";
    }
}
