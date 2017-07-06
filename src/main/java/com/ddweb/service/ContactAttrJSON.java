package com.ddweb.service;

import org.json.JSONObject;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.stereotype.Service;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

/**
 *  JSON filter
 */
@Service
public class ContactAttrJSON implements AttributesMapper {

    /**
     *  @return parsed JSON object to String
     */
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
