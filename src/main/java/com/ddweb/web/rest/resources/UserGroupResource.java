package com.ddweb.web.rest.resources;

import com.ddweb.domain.UserGroup;
import com.ddweb.repository.UserGroupRepository;
import com.ddweb.repository.search.UserGroupSearchRepository;
import com.ddweb.web.rest.util.HeaderUtil;
import com.ddweb.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing UserGroup.
 */
@RestController
@RequestMapping("/api")
public class UserGroupResource {

    private final Logger log = LoggerFactory.getLogger(UserGroupResource.class);

    private static final String ENTITY_NAME = "userGroup";

    private final UserGroupRepository userGroupRepository;

    private final UserGroupSearchRepository userGroupSearchRepository;

    public UserGroupResource(UserGroupRepository userGroupRepository, UserGroupSearchRepository userGroupSearchRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupSearchRepository = userGroupSearchRepository;
    }

    /**
     * POST  /user-groups : Create a new userGroup.
     *
     * @param userGroup the userGroup to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userGroup, or with status 400 (Bad Request) if the userGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-groups")
    public ResponseEntity<UserGroup> createUserGroup(@Valid @RequestBody UserGroup userGroup) throws URISyntaxException {
        log.debug("REST request to save UserGroup : {}", userGroup);
        if (userGroup.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userGroup cannot already have an ID")).body(null);
        }
        UserGroup result = userGroupRepository.save(userGroup);
        userGroupSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/user-groups/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /user-groups : Updates an existing userGroup.
     *
     * @param userGroup the userGroup to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userGroup,
     * or with status 400 (Bad Request) if the userGroup is not valid,
     * or with status 500 (Internal Server Error) if the userGroup couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-groups")
    public ResponseEntity<UserGroup> updateUserGroup(@Valid @RequestBody UserGroup userGroup) throws URISyntaxException {
        log.debug("REST request to update UserGroup : {}", userGroup);
        if (userGroup.getId() == null) {
            return createUserGroup(userGroup);
        }
        UserGroup result = userGroupRepository.save(userGroup);
        userGroupSearchRepository.save(result);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userGroup.getId().toString()))
                .body(result);
    }

    /**
     * GET  /user-groups : get all the userGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userGroups in body
     */
    @GetMapping("/user-groups")
    public ResponseEntity<List<UserGroup>> getAllUserGroups(Pageable pageable) {
        log.debug("REST request to get a page of UserGroups");
        Page<UserGroup> page = userGroupRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-groups/:id : get the "id" userGroup.
     *
     * @param id the id of the userGroup to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userGroup, or with status 404 (Not Found)
     */
    @GetMapping("/user-groups/{id}")
    public ResponseEntity<UserGroup> getUserGroup(@PathVariable Long id) {
        log.debug("REST request to get UserGroup : {}", id);
        UserGroup userGroup = userGroupRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userGroup));
    }

    /**
     * DELETE  /user-groups/:id : delete the "id" userGroup.
     *
     * @param id the id of the userGroup to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-groups/{id}")
    public ResponseEntity<Void> deleteUserGroup(@PathVariable Long id) {
        log.debug("REST request to delete UserGroup : {}", id);
        userGroupRepository.delete(id);
        userGroupSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/user-groups?query=:query : search for the userGroup corresponding
     * to the query.
     *
     * @param query the query of the userGroup search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/user-groups")
    public ResponseEntity<List<UserGroup>> searchUserGroups(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of UserGroups for query {}", query);
        Page<UserGroup> page = userGroupSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/user-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
