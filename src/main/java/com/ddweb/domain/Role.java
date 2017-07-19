package com.ddweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Role.
 */
@Entity
@Table(name = "role")
@Document(indexName = "role")
public class Role implements Serializable {

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

    @ManyToMany
    @JoinTable(name = "role_included_authorization",
            joinColumns = @JoinColumn(name="roles_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="included_authorizations_id", referencedColumnName="id"))
    private Set<Authorization> includedAuthorizations = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "role_excluded_authorization",
            joinColumns = @JoinColumn(name="roles_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="excluded_authorizations_id", referencedColumnName="id"))
    private Set<Authorization> excludedAuthorizations = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "role_actual_role",
            joinColumns = @JoinColumn(name="roles_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="actual_roles_id", referencedColumnName="id"))
    private Set<Authorization> actualRoles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "role_parent_role",
            joinColumns = @JoinColumn(name="roles_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="parent_roles_id", referencedColumnName="id"))
    private Set<Role> parentRoles = new HashSet<>();

    @ManyToMany(mappedBy = "parentRoles")
    @JsonIgnore
    private Set<Role> childRoles = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<ApplicationUser> users = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<UserGroup> userGroups = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Role name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Role description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Authorization> getIncludedAuthorizations() {
        return includedAuthorizations;
    }

    public Role includedAuthorizations(Set<Authorization> authorizations) {
        this.includedAuthorizations = authorizations;
        return this;
    }

    public Role addIncludedAuthorization(Authorization authorization) {
        this.includedAuthorizations.add(authorization);
        authorization.getRoleIncludeds().add(this);
        return this;
    }

    public Role removeIncludedAuthorization(Authorization authorization) {
        this.includedAuthorizations.remove(authorization);
        authorization.getRoleIncludeds().remove(this);
        return this;
    }

    public void setIncludedAuthorizations(Set<Authorization> authorizations) {
        this.includedAuthorizations = authorizations;
    }

    public Set<Authorization> getExcludedAuthorizations() {
        return excludedAuthorizations;
    }

    public Role excludedAuthorizations(Set<Authorization> authorizations) {
        this.excludedAuthorizations = authorizations;
        return this;
    }

    public Role addExcludedAuthorization(Authorization authorization) {
        this.excludedAuthorizations.add(authorization);
        authorization.getRoleExcludeds().add(this);
        return this;
    }

    public Role removeExcludedAuthorization(Authorization authorization) {
        this.excludedAuthorizations.remove(authorization);
        authorization.getRoleExcludeds().remove(this);
        return this;
    }

    public void setExcludedAuthorizations(Set<Authorization> authorizations) {
        this.excludedAuthorizations = authorizations;
    }

    public Set<Authorization> getActualRoles() {
        return actualRoles;
    }

    public Role actualRoles(Set<Authorization> authorizations) {
        this.actualRoles = authorizations;
        return this;
    }

    public Role addActualRole(Authorization authorization) {
        this.actualRoles.add(authorization);
        authorization.getRoleActuals().add(this);
        return this;
    }

    public Role removeActualRole(Authorization authorization) {
        this.actualRoles.remove(authorization);
        authorization.getRoleActuals().remove(this);
        return this;
    }

    public void setActualRoles(Set<Authorization> authorizations) {
        this.actualRoles = authorizations;
    }

    public Set<Role> getParentRoles() {
        return parentRoles;
    }

    public Role parentRoles(Set<Role> roles) {
        this.parentRoles = roles;
        return this;
    }

    public Role addParentRole(Role role) {
        this.parentRoles.add(role);
        role.getChildRoles().add(this);
        return this;
    }

    public Role removeParentRole(Role role) {
        this.parentRoles.remove(role);
        role.getChildRoles().remove(this);
        return this;
    }

    public void setParentRoles(Set<Role> roles) {
        this.parentRoles = roles;
    }

    public Set<Role> getChildRoles() {
        return childRoles;
    }

    public Role childRoles(Set<Role> roles) {
        this.childRoles = roles;
        return this;
    }

    public Role addChildRole(Role role) {
        this.childRoles.add(role);
        role.getParentRoles().add(this);
        return this;
    }

    public Role removeChildRole(Role role) {
        this.childRoles.remove(role);
        role.getParentRoles().remove(this);
        return this;
    }

    public void setChildRoles(Set<Role> roles) {
        this.childRoles = roles;
    }

    public Set<ApplicationUser> getUsers() {
        return users;
    }

    public Role users(Set<ApplicationUser> applicationUsers) {
        this.users = applicationUsers;
        return this;
    }

    public Role addUsers(ApplicationUser applicationUser) {
        this.users.add(applicationUser);
        applicationUser.getRoles().add(this);
        return this;
    }

    public Role removeUsers(ApplicationUser applicationUser) {
        this.users.remove(applicationUser);
        applicationUser.getRoles().remove(this);
        return this;
    }

    public void setUsers(Set<ApplicationUser> applicationUsers) {
        this.users = applicationUsers;
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public Role userGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
        return this;
    }

    public Role addUserGroups(UserGroup userGroup) {
        this.userGroups.add(userGroup);
        userGroup.getRoles().add(this);
        return this;
    }

    public Role removeUserGroups(UserGroup userGroup) {
        this.userGroups.remove(userGroup);
        userGroup.getRoles().remove(this);
        return this;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        if (role.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }
}
