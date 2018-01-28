/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.repositories;

import edu.marius.graph.domain.job.JobDescription;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author MariusCraciunescu
 */
@Repository
public interface JobDescriptionRepository extends GraphRepository<JobDescription> {

  @Query("MATCH (n:JobDescription) WHERE n.industry =~ {0} RETURN DISTINCT n")
  List<JobDescription> findByIndustryRegex(String regex);
  
}
