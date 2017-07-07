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
 *  Test class for connection with LDAP data store
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapConnectionTests {

    @Autowired
    private LdapConnection ldapConnection;

    public LdapConnectionTests() {
    }

    /**
     *  checking if custom entry is not empty
     */
    @Test
    public void connectionWorks()
    {
        List<String> ldapFilters = new ArrayList<>();
        ldapFilters.add("objectclass");
        ldapFilters.add("Person");
        assertThat(ldapConnection.ConnectViaLdap(ldapFilters)).isNotEmpty();
    }
}
