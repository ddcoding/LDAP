package com.ddweb.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "group_entity")
public class UserGroup implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_description")
    private String description;

    @ManyToMany(targetEntity=Role.class ,cascade = CascadeType.ALL)
//    @JoinTable(name = "group_entity_roles",
//            joinColumns = {@JoinColumn(name = "user_groups_user_group_id", referencedColumnName = "user_group_id")},
//            inverseJoinColumns = {@JoinColumn(name = "roles_role_id",referencedColumnName = "role_id")})
//    @Column(name = "roles")
    private List<Role> roles;

    public UserGroup() {
    }

    public UserGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
