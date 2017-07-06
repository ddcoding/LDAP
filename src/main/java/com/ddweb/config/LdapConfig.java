package com.ddweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;


/**
 *  Ldap configuration file
 */
@Configuration
@PropertySource("classpath:application.properties")
public class LdapConfig {
   /**
    *   Variable is used for using properties file
    */
    private final Environment env;
    @Autowired
    public LdapConfig(Environment env) {
        this.env = env;
    }

    /**
     *  Setting LDAP context
     *  @return - LdapTemplate is used for applying filters
     */
    public LdapOperations getTemplate(){
        String url = env.getProperty("LdapUrl");
        String base = env.getProperty("LdapBase");
        String userDn = env.getProperty("LdapUserDn");
        String password = env.getProperty("LdapPassword");
        LdapContextSource ctx = new LdapContextSource();
        ctx.setUrl(url);
        ctx.setBase(base);
        ctx.setUserDn(userDn);
        ctx.setPassword(password);
        ctx.afterPropertiesSet();
        return new LdapTemplate(ctx);
    }
}
