package com.ddweb.ldap;

import org.json.JSONObject;
import org.springframework.ldap.core.AttributesMapper;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

public class ContactAttrJSON implements AttributesMapper {

    @Override
    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        NamingEnumeration<String> ids = attributes.getIDs();
        JSONObject jo = new JSONObject();
        while (ids.hasMore())
        {
            String id = ids.next();
            jo.put(id,attributes.get(id).get());
        }
        return jo.toString();
    }
}
