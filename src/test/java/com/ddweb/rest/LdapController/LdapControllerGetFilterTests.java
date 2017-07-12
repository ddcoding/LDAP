package com.ddweb.rest.LdapController;

import com.ddweb.web.rest.ldap.LdapController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for LDAP rest controller. This class is for test method <b>GET</b> for download filtered <b>USERS</b>
 *
 * @see LdapController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapControllerGetFilterTests {
    private MockMvc ldapRest;
    /**
     * default list used for tests
     */
    private static List<String> defaultStringListofFilters;

    private static List<String> defaultStringListofBadFilters;

    private static List<String> defaultEmptyList;

    private static List<String> defaultNullList;

    private static final String urlRESTapi = "/api/ldap/filters/{ldapFilters}";

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
        defaultEmptyList = new ArrayList<>();
        defaultEmptyList.clear();
        defaultStringListofFilters = new ArrayList<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String toString() {
                String v = super.toString();
                v = v.substring(1, v.length() - 1);
                return v;
            }
        };
        defaultStringListofBadFilters = new ArrayList<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String toString() {
                String v = super.toString();
                v = v.substring(1, v.length() - 1);
                return v;
            }
        };
        defaultStringListofFilters.add("admin");
        defaultStringListofFilters.add("mod");
        defaultStringListofFilters.add("user");

        defaultStringListofBadFilters.add("adminnn");
        defaultStringListofBadFilters.add("bielelelele");
    }

    /**
     * sending get request to tested rest and expect to receive ok status
     * @throws Exception when error with REST occurs
     */
    @Test
    public void getFilteredEntry() throws Exception {
        ldapRest.perform(get(LdapControllerGetFilterTests.urlRESTapi, defaultStringListofFilters))
                .andExpect(status().isOk());
    }

    /**
     * Wrong data test
     * @throws Exception when error with REST occurs
     */
    @Test
    public void getFilteredErr() throws Exception {
        ldapRest.perform(get(LdapControllerGetFilterTests.urlRESTapi,defaultStringListofBadFilters))
                .andExpect(status().isBadRequest());
    }

    /**
     * Empty data test
     * @throws Exception when error with REST occurs
    */
    @Test
    public void getEmptyErr() throws Exception{
        defaultEmptyList.clear();
      ldapRest.perform(get(LdapControllerGetFilterTests.urlRESTapi,defaultEmptyList))
                .andExpect(status().isBadRequest());
    }
    /**
     * Null data test
     * @throws Exception when error with REST occurs
    */
    @Test
    public void getNullErr() throws Exception{
      ldapRest.perform(get(LdapControllerGetFilterTests.urlRESTapi,defaultNullList))
                .andExpect(status().isNotFound());
    }

}
