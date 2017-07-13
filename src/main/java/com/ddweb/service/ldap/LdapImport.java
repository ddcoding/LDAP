package com.ddweb.service.ldap;

import com.ddweb.config.LdapConfig;
import com.ddweb.enums.ConvertType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;




/**
 *  Service, where gets from Ldap are defined
 */
@Service
public class LdapImport {

    private final LdapConnection ldapConnection;

    @Autowired
    public LdapImport(LdapConnection ldapConnection) {
        this.ldapConnection = ldapConnection;
    }

    /**
     *  getting logged user name and surname
     *  @return full name of user
     */
    public List<String> getName(){
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            AndFilter andFilter = new AndFilter();
            String[] splitted = userName.split("@");
            andFilter.and(new EqualsFilter("sAMAccountName",splitted[0]));
            ldapConnection.connectViaLdap(ConvertType.GROUPS,andFilter);
            return ldapConnection.connectViaLdap(ConvertType.NAMES,andFilter);
    }
}
