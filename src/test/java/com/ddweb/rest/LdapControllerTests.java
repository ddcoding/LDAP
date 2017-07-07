package com.ddweb.rest;

import com.ddweb.service.LdapConnectionTests;
import com.ddweb.web.rest.LdapController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *  Test class for LDAP rest controller
 *
 *  @see LdapController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapControllerTests {
    private MockMvc ldapRest;
    /**
    * default list used for tests
     *
    */
    private static List<String> defaultStringListofFilters;

    @Autowired
    private LdapController ldapController;

    @Autowired
    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.ldapRest = MockMvcBuilders.standaloneSetup(ldapController)
                .setMessageConverters(jackson2HttpMessageConverter)
                .build();
    }

    @Before
    public void fillList() {
        defaultStringListofFilters = new ArrayList<String>(){
                private static final long serialVersionUID = 1L;

                @Override public String toString()
                {
                    String v = super.toString();
                    v = v.substring(1,v.length()-1);
                    return v;
                }
            };
        defaultStringListofFilters.add("objectclass");
        defaultStringListofFilters.add("Person");
    }

    /**
     *  sending get request to tested rest and expect to receive ok status
     */
    @Test
    public void getFilteredEntry() throws Exception {
        ldapRest.perform(get("/api/filters/{ldapFilters}", defaultStringListofFilters))
                .andExpect(status().isOk());
    }


}
