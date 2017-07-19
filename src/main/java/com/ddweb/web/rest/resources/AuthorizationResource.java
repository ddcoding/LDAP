package com.ddweb.web.rest.resources;

import com.ddweb.domain.Authorization;
import com.ddweb.repository.AuthorizationRepository;
import com.ddweb.repository.search.AuthorizationSearchRepository;
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
 * REST controller for managing Authorization.
 */
@RestController
@RequestMapping("/api")
public class AuthorizationResource {

    private final Logger log = LoggerFactory.getLogger(AuthorizationResource.class);

    private static final String ENTITY_NAME = "authorization";

    private final AuthorizationRepository authorizationRepository;

    private final AuthorizationSearchRepository authorizationSearchRepository;

    public AuthorizationResource(AuthorizationRepository authorizationRepository, AuthorizationSearchRepository authorizationSearchRepository) {
        this.authorizationRepository = authorizationRepository;
        this.authorizationSearchRepository = authorizationSearchRepository;
    }

    /**
     * POST  /authorizations : Create a new authorization.
     *
     * @param authorization the authorization to create
     * @return the ResponseEntity with status 201 (Created) and with body the new authorization, or with status 400 (Bad Request) if the authorization has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/authorizations")
    public ResponseEntity<Authorization> createAuthorization(@Valid @RequestBody Authorization authorization) throws URISyntaxException {
        log.debug("REST request to save Authorization : {}", authorization);
        if (authorization.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new authorization cannot already have an ID")).body(null);
        }
        Authorization result = authorizationRepository.save(authorization);
        authorizationSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/authorizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /authorizations : Updates an existing authorization.
     *
     * @param authorization the authorization to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated authorization,
     * or with status 400 (Bad Request) if the authorization is not valid,
     * or with status 500 (Internal Server Error) if the authorization couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/authorizations")
    public ResponseEntity<Authorization> updateAuthorization(@Valid @RequestBody Authorization authorization) throws URISyntaxException {
        log.debug("REST request to update Authorization : {}", authorization);
        if (authorization.getId() == null) {
            return createAuthorization(authorization);
        }
        Authorization result = authorizationRepository.save(authorization);
        authorizationSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authorization.getId().toString()))
            .body(result);
    }

    /**
     * GET  /authorizations : get all the authorizations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of authorizations in body
     */
    @GetMapping("/authorizations")
    public ResponseEntity<List<Authorization>> getAllAuthorizations(Pageable pageable) {
        log.debug("REST request to get a page of Authorizations");
        Page<Authorization> page = authorizationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/authorizations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /authorizations/:id : get the "id" authorization.
     *
     * @param id the id of the authorization to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the authorization, or with status 404 (Not Found)
     */
    @GetMapping("/authorizations/{id}")
    public ResponseEntity<Authorization> getAuthorization(@PathVariable Long id) {
        log.debug("REST request to get Authorization : {}", id);
        Authorization authorization = authorizationRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(authorization));
    }

    /**
     * DELETE  /authorizations/:id : delete the "id" authorization.
     *
     * @param id the id of the authorization to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authorizations/{id}")
    public ResponseEntity<Void> deleteAuthorization(@PathVariable Long id) {
        log.debug("REST request to delete Authorization : {}", id);
        authorizationRepository.delete(id);
        authorizationSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/authorizations?query=:query : search for the authorization corresponding
     * to the query.
     *
     * @param query the query of the authorization search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/authorizations")
    public ResponseEntity<List<Authorization>> searchAuthorizations(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Authorizations for query {}", query);
        Page<Authorization> page = authorizationSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/authorizations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
