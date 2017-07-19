//package com.ddweb.rest.LdapController;
//
//import com.ddweb.web.rest.ldap.LdapController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
///**
// * Test class for LDAP rest controller. This class is for test method <b>GET</b> for download <b>GROUPS</b>
// *
// * @see LdapController
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LdapControllerGetGroupsTests {
//    private MockMvc ldapRest;
//    /**
//     * default list used for tests
//     */
//    private List<String> stringList;
//
//    @Autowired
//    private LdapController ldapController;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        this.ldapRest = MockMvcBuilders.standaloneSetup(ldapController)
//                .setMessageConverters(jackson2HttpMessageConverter)
//                .build();
//    }
//
//    @Test
//    public void getGroups() throws Exception {
//        ldapRest.perform(get("/api/ldap/groups"))
//                .andExpect(status().isOk());
//    }
//}
