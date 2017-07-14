package com.ddweb.entities;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "surname")
    private String surname;
    @Column(name = "position")
    private String position;

    public ApplicationUser() {
    }

    public ApplicationUser(String branch, String password, String name, String login, String surname, String position) {
        this.branch = branch;
        this.password = password;
        this.name = name;
        this.login = login;
        this.surname = surname;
        this.position = position;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
