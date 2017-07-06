package com.ddweb.service;

import com.ddweb.config.LdapConfig;
import com.ddweb.service.ContactAttrJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *  Receiving data from LDAP data store
 */
@Service
public class LdapConnection {

    /**
     *  used for getting LdapTemplate
     */
    private final LdapConfig ldapConfig;
    @Autowired
    public LdapConnection(LdapConfig ldapConfig) {
        this.ldapConfig = ldapConfig;
    }

    /**
     *  getting records from LDAP method
     *  @return list of records
     */
    public List<String> ConnectViaLdap()
    {
        //test filters
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass","Person"));
        andFilter.and(new EqualsFilter("cn","Jan"));
        @SuppressWarnings("unchecked")
        List<String> stringList = ldapConfig.getTemplate().search("",andFilter.encode(),new ContactAttrJSON());
        System.out.println(stringList.toString());
        return stringList;
        //end test filters
    }

}
