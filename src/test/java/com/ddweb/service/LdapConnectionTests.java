package com.ddweb.service;

import com.ddweb.enums.ConvertType;
import com.ddweb.model.LdapFilter;
import org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
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
        List<String> ldapFilters = new ArrayList<>();
        ldapFilters.add("ou");
        ldapFilters.add(LdapConnectionTests.GROUP_ADMIN + "," + LdapConnectionTests.GROUP_SUPPORT + "," + env.getProperty("LdapBase"));
        assertThat(ldapConnection.connectViaLdap(ldapFilters, ConvertType.NAMES)).isNotEmpty();
    }
    /**
     * checking if custom entry is not empty with correct data and ConvertType is set to GROUPS
     * @see ConvertType
     */
    @Test
    public void connectionResultsSuccess2() {
        List<String> ldapFilters = new ArrayList<>();
        ldapFilters.add("ou");
        ldapFilters.add("ROLE");
        assertThat(ldapConnection.connectViaLdap(ldapFilters, ConvertType.GROUPS)).isNotEmpty();
    }

    /**
     * checking if custom entry is empty with wrong data
     */
    @Test
    public void connectionResultsFailure() {
        List<String> ldapFilters = new ArrayList<>();
        ldapFilters.add("sometext");
        ldapFilters.add("Wrong");
        assertThat(ldapConnection.connectViaLdap(ldapFilters, ConvertType.NAMES)).isEmpty();
    }

    /**
     * checking if custom entry is null with null data
     */
    @Test
    public void connectionResultsNull() {
        assertThat(ldapConnection.connectViaLdap(null, null)).isNull();
    }

    @Test
    public void connectionResultsNull2() {
        assertThat(ldapConnection.connectViaLdap(null, ConvertType.NAMES)).isNull();
    }

    @Test
    public void connectionResultsNull3() {
        List<String> ldapFilters = new ArrayList<>();
        ldapFilters.add("ou");
        ldapFilters.add(LdapConnectionTests.GROUP_ADMIN + "," + LdapConnectionTests.GROUP_SUPPORT + "," + env.getProperty("LdapBase"));
        assertThat(ldapConnection.connectViaLdap(ldapFilters, null)).isNull();
    }

    /**
     * checking if custom entry is empty with wrong data
     */
    @Test
    public void connectionResultsEmpty() {
        List<String> ldapFilters = new ArrayList<>();
        assertThat(ldapConnection.connectViaLdap(ldapFilters, ConvertType.NAMES)).isNull();
    }

}
