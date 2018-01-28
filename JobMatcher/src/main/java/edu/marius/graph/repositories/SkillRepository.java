/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.repositories;

import edu.marius.graph.domain.cv.Skill;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MariusCraciunescu
 */
@Repository
public interface SkillRepository extends GraphRepository<Skill> {

  @Query("MERGE (:Skill {skill})")
  @Override
  public Skill save(Skill skill);

}
