package com.ddweb.rest.utils;

import com.ddweb.LdapApplication;
import com.ddweb.domain.ApplicationUser;
import com.ddweb.repository.ApplicationUserRepository;
import com.ddweb.repository.search.ApplicationUserSearchRepository;
import com.ddweb.web.rest.errors.ExceptionTranslator;
import com.ddweb.web.rest.resources.ApplicationUserResource;
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
 * Test class for the ApplicationUserResource REST controller.
 *
 * @see ApplicationUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LdapApplication.class)
public class ApplicationUserResourceIntTest {

    private static final String DEFAULT_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private ApplicationUserSearchRepository applicationUserSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restApplicationUserMockMvc;

    private ApplicationUser applicationUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ApplicationUserResource applicationUserResource = new ApplicationUserResource(applicationUserRepository, applicationUserSearchRepository);
        this.restApplicationUserMockMvc = MockMvcBuilders.standaloneSetup(applicationUserResource)
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
    public static ApplicationUser createEntity(EntityManager em) {
        ApplicationUser applicationUser = new ApplicationUser()
            .branch(DEFAULT_BRANCH)
            .name(DEFAULT_NAME)
            .login(DEFAULT_LOGIN)
            .surname(DEFAULT_SURNAME)
            .position(DEFAULT_POSITION)
            .email(DEFAULT_EMAIL);
        return applicationUser;
    }

    @Before
    public void initTest() {
        applicationUserSearchRepository.deleteAll();
        applicationUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createApplicationUser() throws Exception {
        int databaseSizeBeforeCreate = applicationUserRepository.findAll().size();

        // Create the ApplicationUser
        restApplicationUserMockMvc.perform(post("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(applicationUser)))
            .andExpect(status().isCreated());

        // Validate the ApplicationUser in the database
        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeCreate + 1);
        ApplicationUser testApplicationUser = applicationUserList.get(applicationUserList.size() - 1);
        assertThat(testApplicationUser.getBranch()).isEqualTo(DEFAULT_BRANCH);
        assertThat(testApplicationUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testApplicationUser.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testApplicationUser.getSurname()).isEqualTo(DEFAULT_SURNAME);
        assertThat(testApplicationUser.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testApplicationUser.getEmail()).isEqualTo(DEFAULT_EMAIL);

        // Validate the ApplicationUser in Elasticsearch
        ApplicationUser applicationUserEs = applicationUserSearchRepository.findOne(testApplicationUser.getId());
        assertThat(applicationUserEs).isEqualToComparingFieldByField(testApplicationUser);
    }

    @Test
    @Transactional
    public void createApplicationUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = applicationUserRepository.findAll().size();

        // Create the ApplicationUser with an existing ID
        applicationUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApplicationUserMockMvc.perform(post("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(applicationUser)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBranchIsRequired() throws Exception {
        int databaseSizeBeforeTest = applicationUserRepository.findAll().size();
        // set the field null
        applicationUser.setBranch(null);

        // Create the ApplicationUser, which fails.

        restApplicationUserMockMvc.perform(post("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(applicationUser)))
            .andExpect(status().isBadRequest());

        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = applicationUserRepository.findAll().size();
        // set the field null
        applicationUser.setName(null);

        // Create the ApplicationUser, which fails.

        restApplicationUserMockMvc.perform(post("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(applicationUser)))
            .andExpect(status().isBadRequest());

        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoginIsRequired() throws Exception {
        int databaseSizeBeforeTest = applicationUserRepository.findAll().size();
        // set the field null
        applicationUser.setLogin(null);

        // Create the ApplicationUser, which fails.

        restApplicationUserMockMvc.perform(post("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(applicationUser)))
            .andExpect(status().isBadRequest());

        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSurnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = applicationUserRepository.findAll().size();
        // set the field null
        applicationUser.setSurname(null);

        // Create the ApplicationUser, which fails.

        restApplicationUserMockMvc.perform(post("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(applicationUser)))
            .andExpect(status().isBadRequest());

        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPositionIsRequired() throws Exception {
        int databaseSizeBeforeTest = applicationUserRepository.findAll().size();
        // set the field null
        applicationUser.setPosition(null);

        // Create the ApplicationUser, which fails.

        restApplicationUserMockMvc.perform(post("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(applicationUser)))
            .andExpect(status().isBadRequest());

        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = applicationUserRepository.findAll().size();
        // set the field null
        applicationUser.setEmail(null);

        // Create the ApplicationUser, which fails.

        restApplicationUserMockMvc.perform(post("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(applicationUser)))
            .andExpect(status().isBadRequest());

        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllApplicationUsers() throws Exception {
        // Initialize the database
        applicationUserRepository.saveAndFlush(applicationUser);

        // Get all the applicationUserList
        restApplicationUserMockMvc.perform(get("/api/application-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(applicationUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN.toString())))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME.toString())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())));
    }

    @Test
    @Transactional
    public void getApplicationUser() throws Exception {
        // Initialize the database
        applicationUserRepository.saveAndFlush(applicationUser);

        // Get the applicationUser
        restApplicationUserMockMvc.perform(get("/api/application-users/{id}", applicationUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(applicationUser.getId().intValue()))
            .andExpect(jsonPath("$.branch").value(DEFAULT_BRANCH.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN.toString()))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME.toString()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApplicationUser() throws Exception {
        // Get the applicationUser
        restApplicationUserMockMvc.perform(get("/api/application-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApplicationUser() throws Exception {
        // Initialize the database
        applicationUserRepository.saveAndFlush(applicationUser);
        applicationUserSearchRepository.save(applicationUser);
        int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();

        // Update the applicationUser
        ApplicationUser updatedApplicationUser = applicationUserRepository.findOne(applicationUser.getId());
        updatedApplicationUser
            .branch(UPDATED_BRANCH)
            .name(UPDATED_NAME)
            .login(UPDATED_LOGIN)
            .surname(UPDATED_SURNAME)
            .position(UPDATED_POSITION)
            .email(UPDATED_EMAIL);

        restApplicationUserMockMvc.perform(put("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedApplicationUser)))
            .andExpect(status().isOk());

        // Validate the ApplicationUser in the database
        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
        ApplicationUser testApplicationUser = applicationUserList.get(applicationUserList.size() - 1);
        assertThat(testApplicationUser.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testApplicationUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testApplicationUser.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testApplicationUser.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testApplicationUser.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testApplicationUser.getEmail()).isEqualTo(UPDATED_EMAIL);

        // Validate the ApplicationUser in Elasticsearch
        ApplicationUser applicationUserEs = applicationUserSearchRepository.findOne(testApplicationUser.getId());
        assertThat(applicationUserEs).isEqualToComparingFieldByField(testApplicationUser);
    }

    @Test
    @Transactional
    public void updateNonExistingApplicationUser() throws Exception {
        int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();

        // Create the ApplicationUser

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restApplicationUserMockMvc.perform(put("/api/application-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(applicationUser)))
            .andExpect(status().isCreated());

        // Validate the ApplicationUser in the database
        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteApplicationUser() throws Exception {
        // Initialize the database
        applicationUserRepository.saveAndFlush(applicationUser);
        applicationUserSearchRepository.save(applicationUser);
        int databaseSizeBeforeDelete = applicationUserRepository.findAll().size();

        // Get the applicationUser
        restApplicationUserMockMvc.perform(delete("/api/application-users/{id}", applicationUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean applicationUserExistsInEs = applicationUserSearchRepository.exists(applicationUser.getId());
        assertThat(applicationUserExistsInEs).isFalse();

        // Validate the database is empty
        List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
        assertThat(applicationUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchApplicationUser() throws Exception {
        // Initialize the database
        applicationUserRepository.saveAndFlush(applicationUser);
        applicationUserSearchRepository.save(applicationUser);

        // Search the applicationUser
        restApplicationUserMockMvc.perform(get("/api/_search/application-users?query=id:" + applicationUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(applicationUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN.toString())))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME.toString())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApplicationUser.class);
        ApplicationUser applicationUser1 = new ApplicationUser();
        applicationUser1.setId(1L);
        ApplicationUser applicationUser2 = new ApplicationUser();
        applicationUser2.setId(applicationUser1.getId());
        assertThat(applicationUser1).isEqualTo(applicationUser2);
        applicationUser2.setId(2L);
        assertThat(applicationUser1).isNotEqualTo(applicationUser2);
        applicationUser1.setId(null);
        assertThat(applicationUser1).isNotEqualTo(applicationUser2);
    }
}
