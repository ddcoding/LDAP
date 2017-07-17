package com.ddweb.web.rest.security;

import com.ddweb.entities.Authorization;
import com.ddweb.model.User;
import com.ddweb.repositories.AuthorizationRepository;
import com.ddweb.service.ldap.LdapImport;
import com.ddweb.service.ldap.LdapLogged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Controller for all kind of authentication operations
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private LdapLogged ldapLogged;


    private final LdapImport ldapImport;


    @Autowired
    public AuthController(LdapLogged ldapLogged, LdapImport ldapImport) {
        this.ldapLogged = ldapLogged;
        this.ldapImport = ldapImport;
    }

    @GetMapping("/check")
//    @ResponseBody
    public ResponseEntity getAuth()
    {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser" ) {
            //przydaloby sie zrzutowac to authorities
//            List<String> authorizations = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            return new ResponseEntity(HttpStatus.OK);
        }else
            return ResponseEntity.status(401).build();
    }

    /**
     *  <b>GET</b> request to get logged person <i>full name</i>
     *  @return full name of current user
     */
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<List<String>> getName(){
        System.err.print(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null)
            return new ResponseEntity<>(ldapImport.getName(), HttpStatus.ACCEPTED);
        else
            return ResponseEntity.status(401).build();
    }

    /**
     *  <b>POST</b> request to authenticate user's session
     */
    @PostMapping("/login")
    public ResponseEntity logIn(@RequestBody User user){
        if(ldapLogged.isLogged(user.getUserName(), user.getPassword())) {
            //nie moge wbic tu listy atrybutow sprobuje pozniej
//            List<Authorization> list = authorizationRepository.getAllAuthorizations("ROLE_USER");
//            List<String> namedList = new ArrayList<>();
//            for(Authorization a:list) namedList.add(a.getName());
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), null, AuthorityUtils.createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.status(401).build();
    }


    /**
     *  if authorization fails, the user will be redirected to login page
     */
    @RequestMapping("/login/page")
    public void loginHandler(HttpServletResponse response) throws IOException {
        response.sendRedirect("/#/login");
    }
}
