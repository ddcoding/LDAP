package com.ddweb.repositories;

import com.ddweb.entities.Authorization;
import com.ddweb.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization,Long> {
//jakies query do pobrania nazwy roli
//    @Query("select a from Authorization a where a.roles.name = :rolename")
//    List<Authorization> getAllAuthorizations(String rolename);
}
