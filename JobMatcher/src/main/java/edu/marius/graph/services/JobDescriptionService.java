/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.services;

import edu.marius.graph.domain.job.JobDescription;
import edu.marius.graph.repositories.JobDescriptionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MariusCraciunescu
 */
@Service
@Transactional
public class JobDescriptionService {

  @Autowired
  private JobDescriptionRepository repository;

  public JobDescription create(JobDescription jobDescription) {
    return repository.save(jobDescription);
  }

  public void delete(JobDescription jobDescription) {
    repository.delete(jobDescription);
  }

  public JobDescription findById(long id) {
    return repository.findOne(id);
  }

  public Iterable<JobDescription> findAll() {
    return repository.findAll();
  }
  
  public List<JobDescription> findByIndustry(String industry) {
    String regex = ".*" + industry + ".*";
    System.out.println("regex: " + regex);
    return repository.findByIndustryRegex(regex);
  }
}
