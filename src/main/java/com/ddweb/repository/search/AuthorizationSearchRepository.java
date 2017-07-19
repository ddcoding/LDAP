package com.ddweb.repository.search;

import com.ddweb.domain.Authorization;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Authorization entity.
 */
public interface AuthorizationSearchRepository extends ElasticsearchRepository<Authorization, Long> {
}
