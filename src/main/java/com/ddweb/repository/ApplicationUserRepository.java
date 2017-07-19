package com.ddweb.repository;

import com.ddweb.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the ApplicationUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {

    @Query("select distinct application_user from ApplicationUser application_user left join fetch application_user.userGroups")
    List<ApplicationUser> findAllWithEagerRelationships();

    @Query("select application_user from ApplicationUser application_user left join fetch application_user.userGroups where application_user.id =:id")
    ApplicationUser findOneWithEagerRelationships(@Param("id") Long id);

}
