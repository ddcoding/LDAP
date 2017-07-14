package com.ddweb.entities;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "authorization_entity")
public class Authorization implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;

    @Column(name = "auth_name")
    private String name;

    @Column(name = "auth_description")
    private String descirption;

    @ManyToMany(targetEntity=Role.class ,cascade = CascadeType.ALL)
    @Column(name = "roles_excluded")
    @JoinTable(name = "role",
            joinColumns = {@JoinColumn(name = "excluded_auth_id", referencedColumnName = "auth_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")})
    private List<Role> rolesExcluded;

    @ManyToMany(targetEntity=Role.class ,cascade = CascadeType.ALL)
    @Column(name = "roles_included")
    @JoinTable(name = "role",
            joinColumns = {@JoinColumn(name = "included_auth_id", referencedColumnName = "auth_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")})
    private List<Role> rolesIncluded;

    public Authorization() {
    }

    public Authorization(String name, String descirption,List<Role> rolesExcluded, List<Role> rolesIncluded) {
        this.name = name;
        this.descirption = descirption;
        this.rolesExcluded=rolesExcluded;
        this.rolesIncluded=rolesIncluded;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rolesExcluded='" + rolesExcluded + '\'' +
                ", rolesIncluded='" + rolesIncluded + '\'' +
                ", description='" + descirption + '\'' +
                '}';
    }

    public List<Role> getRolesIncluded() {
        return rolesIncluded;
    }

    public void setRolesIncluded(List<Role> rolesIncluded) {
        this.rolesIncluded = rolesIncluded;
    }

    public List<Role> getRolesExcluded() {
        return rolesExcluded;
    }

    public void setRolesExcluded(List<Role> roles) {
        this.rolesExcluded = roles;
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

    public String getDescirption() {
        return descirption;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }
}
