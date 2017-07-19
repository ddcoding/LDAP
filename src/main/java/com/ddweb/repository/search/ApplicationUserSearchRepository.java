package com.ddweb.repository.search;

import com.ddweb.domain.ApplicationUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Elasticsearch repository for the ApplicationUser entity.
 */
@Repository
public interface ApplicationUserSearchRepository extends ElasticsearchRepository<ApplicationUser, Long> {
}
