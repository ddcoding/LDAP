package com.ddweb.repository;

import com.ddweb.domain.ApplicationUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the ApplicationUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {

    @Query("select distinct application_user from ApplicationUser application_user left join fetch application_user.roles")
    List<ApplicationUser> findAllWithEagerRelationships();

    @Query("select application_user from ApplicationUser application_user left join fetch application_user.roles where application_user.id =:id")
    ApplicationUser findOneWithEagerRelationships(@Param("id") Long id);

}
