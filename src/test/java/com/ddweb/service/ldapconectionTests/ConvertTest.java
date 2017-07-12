package com.ddweb.service.ldapconectionTests;


import com.ddweb.config.LdapConfig;
import com.ddweb.enums.ConvertType;
import com.ddweb.service.ldap.ContactAttrJSON;
import com.ddweb.service.ldap.LdapConnection;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.OrFilter;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Test for convert method used in connection to receive data in the right format
 * @see LdapConnection
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConvertTest {

    @Autowired
    private LdapConfig ldapConfig;

    @Autowired
    private LdapConnection ldapConnection;

    private List<Object> listOfCorrectObjects;

    private List<Object> listOfWrongObjects;

    private List<String> correctResultNames;

    private List<String> correctResultGroups;

    @Before
    public void listFilling() {
        OrFilter orFilter = new OrFilter();
        orFilter.or(new EqualsFilter("objectclass", "People"));
        listOfCorrectObjects = ldapConfig.getTemplate().search("", orFilter.encode(), new ContactAttrJSON());

        listOfWrongObjects = new ArrayList<>();
        listOfWrongObjects.add(new Object());

        correctResultNames = new ArrayList<>();
        for (Object o : listOfCorrectObjects)
            correctResultNames.add(((JSONObject) o).get("cn") + " " + ((JSONObject) o).get("sn"));

        correctResultGroups = new ArrayList<>();
        for (Object o : listOfCorrectObjects)
            correctResultGroups.add((String) ((JSONObject) o).get("ou"));
    }

    @Test
    public void convertWithCorrectDataNames() {
        assertThat(ldapConnection.convert(listOfCorrectObjects, ConvertType.NAMES).containsAll(correctResultNames));
    }

    @Test
    public void convertWithCorrectDataGroups()
    {
        assertThat(ldapConnection.convert(listOfCorrectObjects, ConvertType.GROUPS).containsAll(correctResultGroups));
    }

    @Test
    public void convertWithWrongDataNames(){
        assertThat(ldapConnection.convert(listOfWrongObjects,ConvertType.NAMES)).isEmpty();
    }

    @Test
    public void convertWithWrongDataGroups(){
        assertThat(ldapConnection.convert(listOfWrongObjects,ConvertType.GROUPS)).isEmpty();
    }

    @Test
    public void convertWithEmptyDataNames(){
        assertThat(ldapConnection.convert(new ArrayList<>(),ConvertType.NAMES)).isEmpty();
    }

    @Test
    public void convertWithEmptyDataGroups(){
        assertThat(ldapConnection.convert(new ArrayList<>(),ConvertType.GROUPS)).isEmpty();
    }

    @Test
    public void convertWithNullDataNames(){
        assertThat(ldapConnection.convert(null,ConvertType.NAMES)).isNull();
    }

    @Test
    public void convertWithNullDataGroups(){
        assertThat(ldapConnection.convert(null,ConvertType.GROUPS)).isNull();
    }

    @Test
    public void covertWithNullType(){
        assertThat(ldapConnection.convert(listOfCorrectObjects,null)).isNull();
    }

}
