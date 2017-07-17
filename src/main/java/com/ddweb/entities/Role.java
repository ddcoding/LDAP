package com.ddweb.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

    @Column(name = "role_description")
    private String description;

    @Column(name = "parent_role")
    private Role role;

    @Column(name = "included_authorizations")
    @ManyToMany(targetEntity=Authorization.class, mappedBy = "rolesIncluded",cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Authorization> includedAuthorizations;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity=Authorization.class, mappedBy = "rolesExcluded",cascade = CascadeType.ALL)
    @Column(name = "excluded_authorizations")
    private List<Authorization> excludedAuthorizations;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity=Authorization.class,cascade = CascadeType.ALL)
    @Column(name = "authorities")
    private List<Authorization> authorizations = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity=UserGroup.class, mappedBy = "roles",cascade = CascadeType.ALL)
    @Column(name = "user_groups")
    private List<UserGroup> userGroups;

    @ManyToMany(targetEntity=ApplicationUser.class, fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @Column(name = "users")
    private List<ApplicationUser> users;


    public Role() {
    }

    public Role(String name, String description, Role role) {
        this.name = name;
        this.description = description;
        this.role = role;
    }

    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<ApplicationUser> getUsers() {
        return users;
    }

    public void setUsers(List<ApplicationUser> users) {
        this.users = users;
    }

    public List<Authorization> getIncludedAuthorizations() {
        return includedAuthorizations;
    }

    public void setIncludedAuthorizations(List<Authorization> includedAuthorizations) {
        this.includedAuthorizations = includedAuthorizations;
    }

    public List<Authorization> getExcludedAuthorizations() {
        return excludedAuthorizations;
    }

    public void setExcludedAuthorizations(List<Authorization> excludedAuthorizations) {
        this.excludedAuthorizations = excludedAuthorizations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", role=" + role +
                ", includedAuthorizations=" + includedAuthorizations +
                ", excludedAuthorizations=" + excludedAuthorizations +
                ", userGroups=" + userGroups +
                ", users=" + users +
                '}';
    }
}
