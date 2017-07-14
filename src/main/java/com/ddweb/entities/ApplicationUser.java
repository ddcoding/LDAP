package com.ddweb.entities;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_entity")
public class ApplicationUser implements Serializable{
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long id;

    @Column(name = "branch")
    private String branch;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "surname")
    private String surname;

    @Column(name = "position")
    private String position;

    @ManyToMany(targetEntity=Role.class, fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @Column(name = "roles")
    private List<Role> roles;

    @ManyToMany(targetEntity=UserGroup.class, fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @Column(name = "user_groups")
    private List<UserGroup> userGroups;

    public ApplicationUser() {
    }

    public ApplicationUser(String branch, String name, String login, String surname, String position, List<Role> roles, List<UserGroup> userGroups) {
        this.branch = branch;
        this.name = name;
        this.login = login;
        this.surname = surname;
        this.position = position;
        this.roles = roles;
        this.userGroups = userGroups;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", roles=" + roles +
                ", userGroups=" + userGroups +
                '}';
    }
}
