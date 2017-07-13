package com.ddweb.service.ldap;

import com.ddweb.config.LdapConfig;
import com.ddweb.enums.ConvertType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Receiving data from LDAP data store
 */
@Service
public class LdapConnection{

    /**
     * used for getting LdapTemplate
     */
    private final LdapConfig ldapConfig;

    @Autowired
    public LdapConnection(LdapConfig ldapConfig) {
        this.ldapConfig = ldapConfig;
    }

    /**
     * getting records from LDAP method
     *
     * @return list of records
     */
    public List<String> connectViaLdap(ConvertType convertType, AndFilter andFilter) {
        if (convertType != null) {
            @SuppressWarnings("unchecked")
            List<Object> joList = ldapConfig.getTemplate().search("", andFilter.encode(), new ContactAttrJSON());
            return convert(joList, convertType);
        } else return null;
    }

    /**
     * It's method which helps convert JSONObject to specified string
     *
     * @param objectList  - List of JSONObjects to filter
     * @param convertType - Enum for one of cases
     * @return Filtered string list
     * @see ConvertType
     */
    public List<String> convert(List<Object> objectList, ConvertType convertType) {
        if(objectList!=null && convertType !=null) {
            List<String> stringList = new ArrayList<>();
            switch (convertType) {
                case NAMES:
                    for (Object o : objectList)
                        try {
//                            stringList.add(((JSONObject) o).get("cn") + " " + ((JSONObject) o).get("sn"));
                            stringList.add((String) ((JSONObject) o).get("cn"));
                        } catch (ClassCastException ignored) {
                            System.err.println("Got wrong list as param!");
                        }
                    break;
                case GROUPS:
                    for (Object o : objectList)
                        try {
                            stringList.add((String) ((JSONObject) o).get("memberOf"));
                        } catch (ClassCastException ignored) {
                            System.err.println("Got wrong list as param!");
                        }
                        stringList.forEach(System.out::println);
                    break;
            }
            return stringList;
        }else return null;
    }


}
