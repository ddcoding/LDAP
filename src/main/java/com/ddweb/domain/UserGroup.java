package com.ddweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A UserGroup.
 */
@Entity
@Table(name = "user_group")
@Document(indexName = "usergroup")
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "userGroups")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "userGroups")
    @JsonIgnore
    private Set<ApplicationUser> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserGroup name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public UserGroup description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public UserGroup roles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public UserGroup addRoles(Role role) {
        this.roles.add(role);
        role.getUserGroups().add(this);
        return this;
    }

    public UserGroup removeRoles(Role role) {
        this.roles.remove(role);
        role.getUserGroups().remove(this);
        return this;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<ApplicationUser> getUsers() {
        return users;
    }

    public UserGroup users(Set<ApplicationUser> applicationUsers) {
        this.users = applicationUsers;
        return this;
    }

    public UserGroup addUsers(ApplicationUser applicationUser) {
        this.users.add(applicationUser);
        applicationUser.getUserGroups().add(this);
        return this;
    }

    public UserGroup removeUsers(ApplicationUser applicationUser) {
        this.users.remove(applicationUser);
        applicationUser.getUserGroups().remove(this);
        return this;
    }

    public void setUsers(Set<ApplicationUser> applicationUsers) {
        this.users = applicationUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserGroup userGroup = (UserGroup) o;
        if (userGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserGroup{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
