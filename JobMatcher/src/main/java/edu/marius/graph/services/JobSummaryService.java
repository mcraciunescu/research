/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.services;

import edu.marius.graph.domain.job.JobDescription;
import edu.marius.graph.entity.JobSummary;
import edu.marius.graph.repositories.JobDescriptionRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marius
 */
@Service
public class JobSummaryService {

    @Autowired
    private JobDescriptionRepository jobRepository;

    public List<JobSummary> getJobSummaries() {
        return StreamSupport.stream(jobRepository.findAll().spliterator(), false)
                .map(this::toSummary)
                .collect(Collectors.toList());
    }

    public JobSummary toSummary(JobDescription jobDescription) {
        JobSummary summary = new JobSummary();
        summary.setCompany(jobDescription.getCompany());
        summary.setId(jobDescription.getId());
        summary.setTitle(jobDescription.getTitle());
        return summary;
    }
}
