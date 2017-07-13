package com.ddweb.service.ldap;

import com.ddweb.config.LdapConfig;
import com.ddweb.enums.ConvertType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class LdapImport {

    private LdapConfig ldapLogged;

    private final LdapConnection ldapConnection;

    @Autowired
    public LdapImport(LdapConfig ldapLogged, LdapConnection ldapConnection) {
        this.ldapLogged = ldapLogged;
        this.ldapConnection = ldapConnection;
    }

    public List<String> getName(){
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            AndFilter andFilter = new AndFilter();
            String[] splitted = userName.split("@");
            andFilter.and(new EqualsFilter("sAMAccountName",splitted[0]));
            @SuppressWarnings("unchecked")
            List<Object> joList = ldapLogged.getTemplate().search("", andFilter.encode(), new ContactAttrJSON());
            List<String> stringList;
            stringList = ldapConnection.convert(joList, ConvertType.NAMES);
            return stringList;
    }
}
