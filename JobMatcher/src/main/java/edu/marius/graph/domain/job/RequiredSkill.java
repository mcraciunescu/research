/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.domain.job;

import java.util.List;
import java.util.Objects;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author MariusCraciunescu
 */
public class RequiredSkill {

    @GraphId
    private Long id;

    private String name;

    @Relationship(type = "REQ_KNOWS", direction = Relationship.INCOMING)
    private List<RequiredSkill> roles;

    public RequiredSkill() {
    }

    public RequiredSkill(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RequiredSkill> getRoles() {
        return roles;
    }

    public void setRoles(List<RequiredSkill> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Skill{" + "id=" + id + ", name=" + name + ", roles=" + roles + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RequiredSkill other = (RequiredSkill) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
