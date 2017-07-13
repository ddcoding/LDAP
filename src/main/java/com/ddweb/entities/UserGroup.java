package com.ddweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "group")
public class UserGroup implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_description")
    private String description;

    public UserGroup() {
    }

    public UserGroup(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "UserGroup{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
