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
public class EducationRequirement {

    @GraphId
    private Long id;

    private String name;

    @Relationship(type = "REQ_LEARNED_AT", direction = Relationship.INCOMING)
    private List<EducationRequirement> qualifications;

    public EducationRequirement() {
    }

    public EducationRequirement(String name) {
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

    public List<EducationRequirement> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<EducationRequirement> qualifications) {
        this.qualifications = qualifications;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.name);
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
        final EducationRequirement other = (EducationRequirement) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EducationRequirement{" + "id=" + id + ", name=" + name + ", qualifications=" + qualifications + '}';
    }

}
