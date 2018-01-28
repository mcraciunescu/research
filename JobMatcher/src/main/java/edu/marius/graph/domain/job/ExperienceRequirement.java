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
public class ExperienceRequirement {

    @GraphId
    private Long id;

    private String name;

    @Relationship(type = "REQ_IS_EXPERIENCED", direction = Relationship.INCOMING)
    private List<ExperienceRequirement> experienceRequirements;

    public ExperienceRequirement() {
    }

    public ExperienceRequirement(String name) {
        this.name = name;
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

    public List<ExperienceRequirement> getExperienceRequirements() {
        return experienceRequirements;
    }

    public void setExperienceRequirements(List<ExperienceRequirement> experienceRequirements) {
        this.experienceRequirements = experienceRequirements;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.name);
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
        final ExperienceRequirement other = (ExperienceRequirement) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExperienceRequirement{" + "id=" + id + ", name=" + name + ", experienceRequirements="
                + experienceRequirements + '}';
    }

}
