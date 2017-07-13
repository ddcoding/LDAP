package com.ddweb.service.ldapconectionTests;

import com.ddweb.enums.ConvertType;
import com.ddweb.service.ldap.LdapConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for connection with LDAP data store
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapConnectionTests {

    private static final String GROUP_ADMIN = "ou=admin";

    private static final String GROUP_SUPPORT = "ou=Support";

    @Autowired
    private LdapConnection ldapConnection;

    @Autowired
    private Environment env;

    /**
     * checking if custom entry is not empty with correct data and ConvertType is set to NAMES
     * @see ConvertType
     */
    @Test
    public void connectionResultsSuccess() {
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("sAMAccountName","jakdwo"));
        assertThat(ldapConnection.connectViaLdap(ConvertType.NAMES,andFilter)).isNotEmpty();
    }

    /**
     * checking if custom entry is empty with wrong data
     */
    @Test
    public void connectionResultsFailure() {
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("sAMAccountName","niemamnie"));
        assertThat(ldapConnection.connectViaLdap(ConvertType.NAMES,andFilter)).isEmpty();
    }

//    /**
//     * checking if custom entry is not empty with correct data and ConvertType is set to GROUPS
//     * @see ConvertType
//     */
//    @Test
//    public void connectionResultsSuccess2() {
//        List<String> ldapFilters = new ArrayList<>();
//        ldapFilters.add("objectclass");
//        ldapFilters.add("group");
//        assertThat(ldapConnection.connectViaLdap(ldapFilters, ConvertType.GROUPS)).isNotEmpty();
//    }
    /**
     * checking if custom entry is null with null data
     */
    @Test
    public void connectionResultsNull() {
        assertThat(ldapConnection.connectViaLdap(null, null)).isNull();
    }
}
