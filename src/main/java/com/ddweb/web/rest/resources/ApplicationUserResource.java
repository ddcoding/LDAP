package com.ddweb.web.rest.resources;

import com.ddweb.domain.ApplicationUser;
import com.ddweb.repository.ApplicationUserRepository;
import com.ddweb.repository.search.ApplicationUserSearchRepository;
import com.ddweb.web.rest.util.HeaderUtil;
import com.ddweb.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * REST controller for managing ApplicationUser.
 */
@RestController
@RequestMapping("/api")
public class ApplicationUserResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserResource.class);

    private static final String ENTITY_NAME = "applicationUser";

    private final ApplicationUserRepository applicationUserRepository;

    private final ApplicationUserSearchRepository applicationUserSearchRepository;

    public ApplicationUserResource(ApplicationUserRepository applicationUserRepository, ApplicationUserSearchRepository applicationUserSearchRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationUserSearchRepository = applicationUserSearchRepository;
    }

    /**
     * POST  /application-users : Create a new applicationUser.
     *
     * @param applicationUser the applicationUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new applicationUser, or with status 400 (Bad Request) if the applicationUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/application-users")
    public ResponseEntity<ApplicationUser> createApplicationUser(@Valid @RequestBody ApplicationUser applicationUser) throws URISyntaxException {
        log.debug("REST request to save ApplicationUser : {}", applicationUser);
        if (applicationUser.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new applicationUser cannot already have an ID")).body(null);
        }
        ApplicationUser result = applicationUserRepository.save(applicationUser);
        applicationUserSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/application-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /application-users : Updates an existing applicationUser.
     *
     * @param applicationUser the applicationUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated applicationUser,
     * or with status 400 (Bad Request) if the applicationUser is not valid,
     * or with status 500 (Internal Server Error) if the applicationUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/application-users")
    public ResponseEntity<ApplicationUser> updateApplicationUser(@Valid @RequestBody ApplicationUser applicationUser) throws URISyntaxException {
        log.debug("REST request to update ApplicationUser : {}", applicationUser);
        if (applicationUser.getId() == null) {
            return createApplicationUser(applicationUser);
        }
        ApplicationUser result = applicationUserRepository.save(applicationUser);
        applicationUserSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, applicationUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /application-users : get all the applicationUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of applicationUsers in body
     */
    @GetMapping("/application-users")
    public ResponseEntity<List<ApplicationUser>> getAllApplicationUsers(Pageable pageable) {
        log.debug("REST request to get a page of ApplicationUsers");
        Page<ApplicationUser> page = applicationUserRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/application-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /application-users/:id : get the "id" applicationUser.
     *
     * @param id the id of the applicationUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the applicationUser, or with status 404 (Not Found)
     */
    @GetMapping("/application-users/{id}")
    public ResponseEntity<ApplicationUser> getApplicationUser(@PathVariable Long id) {
        log.debug("REST request to get ApplicationUser : {}", id);
        ApplicationUser applicationUser = applicationUserRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(applicationUser));
    }

    /**
     * DELETE  /application-users/:id : delete the "id" applicationUser.
     *
     * @param id the id of the applicationUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/application-users/{id}")
    public ResponseEntity<Void> deleteApplicationUser(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationUser : {}", id);
        applicationUserRepository.delete(id);
        applicationUserSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/application-users?query=:query : search for the applicationUser corresponding
     * to the query.
     *
     * @param query the query of the applicationUser search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/application-users")
    public ResponseEntity<List<ApplicationUser>> searchApplicationUsers(@RequestParam String query,Pageable pageable) {
        log.debug("REST request to search for a page of ApplicationUsers for query {}", query);
        Page<ApplicationUser> page = applicationUserSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/application-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
