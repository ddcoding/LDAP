package com.ddweb.rest.utils;

import com.ddweb.LdapApplication;
import com.ddweb.domain.Authorization;
import com.ddweb.repository.AuthorizationRepository;
import com.ddweb.repository.search.AuthorizationSearchRepository;
import com.ddweb.web.rest.errors.ExceptionTranslator;
import com.ddweb.web.rest.resources.AuthorizationResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AuthorizationResource REST controller.
 *
 * @see AuthorizationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LdapApplication.class)
public class AuthorizationResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Autowired
    private AuthorizationSearchRepository authorizationSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAuthorizationMockMvc;

    private Authorization authorization;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AuthorizationResource authorizationResource = new AuthorizationResource(authorizationRepository, authorizationSearchRepository);
        this.restAuthorizationMockMvc = MockMvcBuilders.standaloneSetup(authorizationResource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setControllerAdvice(exceptionTranslator)
                .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Authorization createEntity(EntityManager em) {
        Authorization authorization = new Authorization()
                .name(DEFAULT_NAME)
                .description(DEFAULT_DESCRIPTION);
        return authorization;
    }

    @Before
    public void initTest() {
        authorizationSearchRepository.deleteAll();
        authorization = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuthorization() throws Exception {
        int databaseSizeBeforeCreate = authorizationRepository.findAll().size();

        // Create the Authorization
        restAuthorizationMockMvc.perform(post("/api/authorizations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(authorization)))
                .andExpect(status().isCreated());

        // Validate the Authorization in the database
        List<Authorization> authorizationList = authorizationRepository.findAll();
        assertThat(authorizationList).hasSize(databaseSizeBeforeCreate + 1);
        Authorization testAuthorization = authorizationList.get(authorizationList.size() - 1);
        assertThat(testAuthorization.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAuthorization.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);

        // Validate the Authorization in Elasticsearch
        Authorization authorizationEs = authorizationSearchRepository.findOne(testAuthorization.getId());
        assertThat(authorizationEs).isEqualToComparingFieldByField(testAuthorization);
    }

    @Test
    @Transactional
    public void createAuthorizationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = authorizationRepository.findAll().size();

        // Create the Authorization with an existing ID
        authorization.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuthorizationMockMvc.perform(post("/api/authorizations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(authorization)))
                .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Authorization> authorizationList = authorizationRepository.findAll();
        assertThat(authorizationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = authorizationRepository.findAll().size();
        // set the field null
        authorization.setName(null);

        // Create the Authorization, which fails.

        restAuthorizationMockMvc.perform(post("/api/authorizations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(authorization)))
                .andExpect(status().isBadRequest());

        List<Authorization> authorizationList = authorizationRepository.findAll();
        assertThat(authorizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAuthorizations() throws Exception {
        // Initialize the database
        authorizationRepository.saveAndFlush(authorization);

        // Get all the authorizationList
        restAuthorizationMockMvc.perform(get("/api/authorizations?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(authorization.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getAuthorization() throws Exception {
        // Initialize the database
        authorizationRepository.saveAndFlush(authorization);

        // Get the authorization
        restAuthorizationMockMvc.perform(get("/api/authorizations/{id}", authorization.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value(authorization.getId().intValue()))
                .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAuthorization() throws Exception {
        // Get the authorization
        restAuthorizationMockMvc.perform(get("/api/authorizations/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuthorization() throws Exception {
        // Initialize the database
        authorizationRepository.saveAndFlush(authorization);
        authorizationSearchRepository.save(authorization);
        int databaseSizeBeforeUpdate = authorizationRepository.findAll().size();

        // Update the authorization
        Authorization updatedAuthorization = authorizationRepository.findOne(authorization.getId());
        updatedAuthorization
                .name(UPDATED_NAME)
                .description(UPDATED_DESCRIPTION);

        restAuthorizationMockMvc.perform(put("/api/authorizations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedAuthorization)))
                .andExpect(status().isOk());

        // Validate the Authorization in the database
        List<Authorization> authorizationList = authorizationRepository.findAll();
        assertThat(authorizationList).hasSize(databaseSizeBeforeUpdate);
        Authorization testAuthorization = authorizationList.get(authorizationList.size() - 1);
        assertThat(testAuthorization.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAuthorization.getDescription()).isEqualTo(UPDATED_DESCRIPTION);

        // Validate the Authorization in Elasticsearch
        Authorization authorizationEs = authorizationSearchRepository.findOne(testAuthorization.getId());
        assertThat(authorizationEs).isEqualToComparingFieldByField(testAuthorization);
    }

    @Test
    @Transactional
    public void updateNonExistingAuthorization() throws Exception {
        int databaseSizeBeforeUpdate = authorizationRepository.findAll().size();

        // Create the Authorization

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAuthorizationMockMvc.perform(put("/api/authorizations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(authorization)))
                .andExpect(status().isCreated());

        // Validate the Authorization in the database
        List<Authorization> authorizationList = authorizationRepository.findAll();
        assertThat(authorizationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAuthorization() throws Exception {
        // Initialize the database
        authorizationRepository.saveAndFlush(authorization);
        authorizationSearchRepository.save(authorization);
        int databaseSizeBeforeDelete = authorizationRepository.findAll().size();

        // Get the authorization
        restAuthorizationMockMvc.perform(delete("/api/authorizations/{id}", authorization.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean authorizationExistsInEs = authorizationSearchRepository.exists(authorization.getId());
        assertThat(authorizationExistsInEs).isFalse();

        // Validate the database is empty
        List<Authorization> authorizationList = authorizationRepository.findAll();
        assertThat(authorizationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAuthorization() throws Exception {
        // Initialize the database
        authorizationRepository.saveAndFlush(authorization);
        authorizationSearchRepository.save(authorization);

        // Search the authorization
        restAuthorizationMockMvc.perform(get("/api/_search/authorizations?query=id:" + authorization.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(authorization.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Authorization.class);
        Authorization authorization1 = new Authorization();
        authorization1.setId(1L);
        Authorization authorization2 = new Authorization();
        authorization2.setId(authorization1.getId());
        assertThat(authorization1).isEqualTo(authorization2);
        authorization2.setId(2L);
        assertThat(authorization1).isNotEqualTo(authorization2);
        authorization1.setId(null);
        assertThat(authorization1).isNotEqualTo(authorization2);
    }
}
