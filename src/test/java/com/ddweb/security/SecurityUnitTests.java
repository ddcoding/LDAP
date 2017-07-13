package com.ddweb.security;

import com.ddweb.model.User;
import com.ddweb.rest.LdapController.LdapControllerGetFilterTests;
import com.ddweb.service.ldap.LdapImport;
import com.ddweb.utils.TestUtil;
import com.ddweb.web.rest.security.AuthController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luaj.vm2.ast.Str;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Tests for LDAP Security
 * @see AuthController
 * @see LdapImport
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityUnitTests {

    private MockMvc securityRest;

    private static final String urlSecurityRest = "/api/auth/login" ;

    @Autowired
   private LdapImport ldapImport;

    @Autowired
    private AuthController authController;

    @Autowired
    private Environment env;

    @Autowired
    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.securityRest = MockMvcBuilders.standaloneSetup(authController)
                .setMessageConverters(jackson2HttpMessageConverter)
                .build();
    }

        @Test
        public void testgetCurrentUserLogin() {
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(env.getProperty("LdapUserDn") + env.getProperty("LdapDomain"), null));
            SecurityContextHolder.setContext(securityContext);
            String login = ldapImport.getName().get(0);
//            Below surname and name should be enter propably
            assertThat(login).isEqualTo("Dwojak Jakub");
        }

        @Test
        public void testIsAuthenticated() throws Exception {
            User user = new User(env.getProperty("LdapUserDn"), env.getProperty("LdapPassword"));

            securityRest.perform(post(SecurityUnitTests.urlSecurityRest)
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(user)))
                    .andExpect(status().isOk());
        }
//
//        @Test
//        public void testAnonymousIsNotAuthenticated() {
//            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//            Collection<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ANONYMOUS));
//            securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("anonymous", "anonymous", authorities));
//            SecurityContextHolder.setContext(securityContext);
//            boolean isAuthenticated = SecurityUtils.isAuthenticated();
//            assertThat(isAuthenticated).isFalse();
//        }
//
//        @Test
//        public void testIsCurrentUserInRole() {
//            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//            Collection<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.USER));
//            securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("user", "user", authorities));
//            SecurityContextHolder.setContext(securityContext);
//
//            assertThat(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)).isTrue();
//            assertThat(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)).isFalse();
//        }

    }
