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
 * A ApplicationUser.
 */
@Entity
@Table(name = "application_user")
@Document(indexName = "applicationuser")
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "branch", nullable = false)
    private String branch;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "login", nullable = false)
    private String login;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "position", nullable = false)
    private String position;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany
    @JoinTable(name = "application_user_user_groups",
            joinColumns = @JoinColumn(name="application_users_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="user_groups_id", referencedColumnName="id"))
    private Set<UserGroup> userGroups = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public ApplicationUser branch(String branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public ApplicationUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public ApplicationUser login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public ApplicationUser surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public ApplicationUser position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public ApplicationUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public ApplicationUser userGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
        return this;
    }

    public ApplicationUser addUserGroups(UserGroup userGroup) {
        this.userGroups.add(userGroup);
        userGroup.getUsers().add(this);
        return this;
    }

    public ApplicationUser removeUserGroups(UserGroup userGroup) {
        this.userGroups.remove(userGroup);
        userGroup.getUsers().remove(this);
        return this;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public ApplicationUser roles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public ApplicationUser addRoles(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
        return this;
    }

    public ApplicationUser removeRoles(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
        return this;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationUser applicationUser = (ApplicationUser) o;
        if (applicationUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), applicationUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + getId() +
                ", branch='" + getBranch() + "'" +
                ", name='" + getName() + "'" +
                ", login='" + getLogin() + "'" +
                ", surname='" + getSurname() + "'" +
                ", position='" + getPosition() + "'" +
                ", email='" + getEmail() + "'" +
                "}";
    }
}
