package com.ddweb.repository.search;

import com.ddweb.domain.Role;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Elasticsearch repository for the Role entity.
 */
@Repository
public interface RoleSearchRepository extends ElasticsearchRepository<Role, Long> {
}
