package com.ddweb.repository.search;

import com.ddweb.domain.UserGroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Elasticsearch repository for the UserGroup entity.
 */
@Repository
public interface UserGroupSearchRepository extends ElasticsearchRepository<UserGroup, Long> {
}
