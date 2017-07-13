package com.ddweb.service.ldap;

import org.luaj.vm2.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.AuthenticationException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LdapLogged {

    @Autowired
    public LdapLogged(Environment env) {
        this.env = env;
    }

    private Environment env;

    private LdapTemplate ldapTemplate;

    public LdapTemplate getLdapTemplate(String userName, String password) {
        LdapContextSource ctx = new LdapContextSource();
        ctx.setUrl(env.getProperty("LdapUrl"));
        ctx.setBase(env.getProperty("LdapBase"));
        ctx.setUserDn(userName);
        ctx.setPassword(password);
        ctx.afterPropertiesSet();
        ldapTemplate = new LdapTemplate(ctx);
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }


    public boolean isLogged(String userName, String password) {
        List<Object> joList = new ArrayList<>();
        userName = userName + env.getProperty("LdapDomain");
        LdapTemplate ldapTemplate = getLdapTemplate(userName, password);
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectClass", "Person"));
        try {
            joList = ldapTemplate.search("", andFilter.encode(), new ContactAttrJSON());
        } catch (AuthenticationException e) {
            System.err.println("BLEDNY LOGIN LUB HASLO !");
        }
        return !joList.isEmpty();
    }

}
