package com.ddweb.domain;

import com.ddweb.domain.Role;
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
 * A Authorization.
 */
@Entity
@Table(name = "jhi_authorization")
@Document(indexName = "authorization")
public class Authorization implements Serializable {

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

    @ManyToMany(mappedBy = "includedAuthorizations")
    @JsonIgnore
    private Set<Role> roleIncludeds = new HashSet<>();

    @ManyToMany(mappedBy = "excludedAuthorizations")
    @JsonIgnore
    private Set<Role> roleExcludeds = new HashSet<>();

    @ManyToMany(mappedBy = "actualRoles")
    @JsonIgnore
    private Set<Role> roleActuals = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Authorization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Authorization description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoleIncludeds() {
        return roleIncludeds;
    }

    public Authorization roleIncludeds(Set<Role> roles) {
        this.roleIncludeds = roles;
        return this;
    }

    public Authorization addRoleIncluded(Role role) {
        this.roleIncludeds.add(role);
        role.getIncludedAuthorizations().add(this);
        return this;
    }

    public Authorization removeRoleIncluded(Role role) {
        this.roleIncludeds.remove(role);
        role.getIncludedAuthorizations().remove(this);
        return this;
    }

    public void setRoleIncludeds(Set<Role> roles) {
        this.roleIncludeds = roles;
    }

    public Set<Role> getRoleExcludeds() {
        return roleExcludeds;
    }

    public Authorization roleExcludeds(Set<Role> roles) {
        this.roleExcludeds = roles;
        return this;
    }

    public Authorization addRoleExcluded(Role role) {
        this.roleExcludeds.add(role);
        role.getExcludedAuthorizations().add(this);
        return this;
    }

    public Authorization removeRoleExcluded(Role role) {
        this.roleExcludeds.remove(role);
        role.getExcludedAuthorizations().remove(this);
        return this;
    }

    public void setRoleExcludeds(Set<Role> roles) {
        this.roleExcludeds = roles;
    }

    public Set<Role> getRoleActuals() {
        return roleActuals;
    }

    public Authorization roleActuals(Set<Role> roles) {
        this.roleActuals = roles;
        return this;
    }

    public Authorization addRoleActual(Role role) {
        this.roleActuals.add(role);
        role.getActualRoles().add(this);
        return this;
    }

    public Authorization removeRoleActual(Role role) {
        this.roleActuals.remove(role);
        role.getActualRoles().remove(this);
        return this;
    }

    public void setRoleActuals(Set<Role> roles) {
        this.roleActuals = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authorization authorization = (Authorization) o;
        if (authorization.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authorization.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }
}
