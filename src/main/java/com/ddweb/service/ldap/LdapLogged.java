package com.ddweb.service.ldap;

import org.luaj.vm2.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LdapLogged {

    @Autowired
    public LdapLogged(Environment env) {
        this.env = env;
    }

    private Environment env;


    public LdapTemplate getLdapTemplate(String userName, String password)
    {
        LdapContextSource ctx = new LdapContextSource();
        ctx.setUrl(env.getProperty("LdapUrl"));
        ctx.setBase(env.getProperty("LdapBase"));
        ctx.setUserDn(userName);
        ctx.setPassword(password);
        ctx.afterPropertiesSet();
        LdapTemplate ldapTemplate = new LdapTemplate(ctx);
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }


    public boolean isLogged(String userName, String password){
        LdapTemplate ldapTemplate = getLdapTemplate(userName, password);
        AndFilter andFilter = new AndFilter();
            andFilter.and(new EqualsFilter("cn","Microsoft"));
        @SuppressWarnings("unchecked")
        List<Object> joList = ldapTemplate.search("", andFilter.encode(), new ContactAttrJSON());
        if(!joList.isEmpty()) return true;
        else return false;
    }

}
