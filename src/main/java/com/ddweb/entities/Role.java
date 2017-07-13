package com.ddweb.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Role")
public class Role {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "role_name")
    private String name;
    @Column(name = "role_authorizations")
    private List<String> authorizations;

    public Role() {
    }

    public Role(String name, List<String> authorizations) {
        this.name = name;
        this.authorizations = authorizations;
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

    public List<String> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<String> authorizations) {
        this.authorizations = authorizations;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorizations=" + authorizations +
                '}';
    }
}
