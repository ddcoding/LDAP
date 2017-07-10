package com.ddweb.service;

import com.ddweb.model.LdapFilter;
import org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Autowired
    private LdapConnection ldapConnection;

    /**
     * checking if custom entry is not empty with correct data
     */
    @Test
    public void connectionResultsSuccess() {
        List<String> ldapFilters = new ArrayList<>();
        ldapFilters.add("ou");
        ldapFilters.add("admin");
        assertThat(ldapConnection.ConnectViaLdap(ldapFilters)).isNotEmpty();
    }

    /**
     * checking if custom entry is empty with wrong data
     */
    @Test
    public void connectionResultsFailure() {
        List<String> ldapFilters = new ArrayList<>();
        ldapFilters.add("sometext");
        ldapFilters.add("Wrong");
        assertThat(ldapConnection.ConnectViaLdap(ldapFilters)).isEmpty();
    }

    /**
     * checking if custom entry is null with null data
     */
    @Test
    public void connectionResultsNull() {
        assertThat(ldapConnection.ConnectViaLdap(null)).isNull();
    }

    /**
     * checking if custom entry is empty with wrong data
     */
    @Test
    public void connectionResultsEmpty() {
        List<String> ldapFilters = new ArrayList<>();
        assertThat(ldapConnection.ConnectViaLdap(ldapFilters)).isNull();
    }

}
