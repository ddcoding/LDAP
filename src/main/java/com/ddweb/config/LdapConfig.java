package com.ddweb.config;

import com.ddweb.annotations.DevProfile;
import com.ddweb.annotations.ProdProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Ldap configuration file
 */
@Configuration
public class LdapConfig {
    /**
     * Variable is used for using properties file
     */
    private final Environment env;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LdapConfig(Environment env, AuthenticationManager authenticationManager) {
        this.env = env;
        this.authenticationManager = authenticationManager;
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
        System.out.print("logging");
        return ldapTemplate;
    }
}
