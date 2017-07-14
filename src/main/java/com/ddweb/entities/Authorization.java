package com.ddweb.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    public Authorization() {
    }

    public Authorization(String name, String descirption) {
        this.name = name;
        this.descirption = descirption;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descirption='" + descirption + '\'' +
                '}';
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
