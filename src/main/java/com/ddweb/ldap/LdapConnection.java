package com.ddweb.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource("classpath:application.properties")
public class LdapConnection {

    private final Environment env;

    @Autowired
    public LdapConnection(Environment env) {
        this.env = env;
    }

    public void ConnectViaLdap()
    {
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
        LdapTemplate lt = new LdapTemplate(ctx);


//        Query Test Connect
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass","Person"));
        andFilter.and(new EqualsFilter("cn","Jan"));
//        andFilter.and(new EqualsFilter("objectclass","OrganizationalUnit"));
        @SuppressWarnings("unchecked")
        List<String> stringList = lt.search("",andFilter.encode(),new ContactAttrJSON());
        System.out.println(stringList.toString());

    }

}
