package com.ddweb.service;

import com.ddweb.config.LdapConfig;
import com.ddweb.model.LdapFilter;
import com.ddweb.service.ContactAttrJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<String> ConnectViaLdap(List<LdapFilter> ldapFiltersList)
    {
        AndFilter andFilter = new AndFilter();
        for(LdapFilter ldapFilter : ldapFiltersList ){
            andFilter.and(new EqualsFilter(ldapFilter.getAttribute(),ldapFilter.getValue()));
        }
        @SuppressWarnings("unchecked")
        List<String> stringList = ldapConfig.getTemplate().search("",andFilter.encode(),new ContactAttrJSON());
        System.out.println(stringList.toString());
        return stringList;
        //end test filters
    }

}
