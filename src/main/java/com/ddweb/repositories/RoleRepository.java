package com.ddweb.repositories;

import com.ddweb.entities.Authorization;
import com.ddweb.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("select r from Role r where r.name = :rolename")
    Role findByName(String rolename);
}
