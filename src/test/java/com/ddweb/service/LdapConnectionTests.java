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
 * Created by kardro on 06.07.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapConnectionTests {

    @Autowired
    private LdapConnection ldapConnection;

    public LdapConnectionTests() {
    }

    @Test
    public void connectionWorks()
    {
        List<LdapFilter> ldapFilters = new ArrayList<>();
        ldapFilters.add(new LdapFilter("objectclass","Person"));
        assertThat(ldapConnection.ConnectViaLdap(ldapFilters)).isNotEmpty();
    }
}
