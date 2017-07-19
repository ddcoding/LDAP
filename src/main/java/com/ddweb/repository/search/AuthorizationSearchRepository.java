package com.ddweb.repository.search;

import com.ddweb.domain.Authorization;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Elasticsearch repository for the Authorization entity.
 */
@Repository
public interface AuthorizationSearchRepository extends ElasticsearchRepository<Authorization, Long> {
}
