package com.ddweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;


/**
 * Ldap configuration file
 */
@Configuration
public class LdapConfig {
    /**
     * Variable is used for using properties file
     */
    private final Environment env;

    @Autowired
    public LdapConfig(Environment env) {
        this.env = env;
    }

    /**
     * Setting LDAP context
     *
     * @return - LdapTemplate is used for applying filters
     */
    public LdapOperations getTemplate() {
        String url = env.getProperty("LdapUrl");
        String base = env.getProperty("LdapBase");
        String userDn = env.getProperty("LdapUserDn") + env.getProperty("LdapDomain");
        String password = env.getProperty("LdapPassword");
        LdapContextSource ctx = new LdapContextSource();
        ctx.setUrl(url);
        ctx.setBase(base);
        ctx.setUserDn(userDn);
        ctx.setPassword(password);
        ctx.afterPropertiesSet();
        LdapTemplate ldapTemplate = new LdapTemplate(ctx);
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }
}
