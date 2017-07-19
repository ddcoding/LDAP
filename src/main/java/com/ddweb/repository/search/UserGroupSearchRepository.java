package com.ddweb.repository.search;

import com.ddweb.domain.UserGroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UserGroup entity.
 */
public interface UserGroupSearchRepository extends ElasticsearchRepository<UserGroup, Long> {
}
