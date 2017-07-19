package com.ddweb.repository.search;

import com.ddweb.domain.ApplicationUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ApplicationUser entity.
 */
public interface ApplicationUserSearchRepository extends ElasticsearchRepository<ApplicationUser, Long> {
}
