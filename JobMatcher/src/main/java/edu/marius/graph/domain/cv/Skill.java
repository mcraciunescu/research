/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.domain.cv;

import java.util.List;
import java.util.Objects;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author MariusCraciunescu
 */
@NodeEntity
public class Skill {
  @GraphId
  private Long id;

  private String name;

  @Relationship(type = "KNOWS", direction = Relationship.INCOMING)
  private List<Skill> roles;

  public Skill(){}
  
  public Skill(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Skill> getRoles() {
    return roles;
  }

  public void setRoles(List<Skill> roles) {
    this.roles = roles;
  }

  @Override
  public int hashCode() {
    int hash = 5;
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
    final Skill other = (Skill) obj;
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    return true;
  }

}
