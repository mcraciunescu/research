/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.service;

import edu.marius.graph.entity.JobSummary;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Marius
 */
@Singleton
@Startup
public class JobSummaryCacheService {

    @EJB
    JobService jobService;
    private List<JobSummary> jobSummaries;

    @PostConstruct
    public void init() {
        System.out.println("Loading jobs..");
        long start = System.currentTimeMillis();
        //this.jobSummaries = jobService.getJobSummaries();
        long duration = System.currentTimeMillis() - start;
        System.out.println("Loading jobs took: " + duration + " ms.");
    }

    public List<JobSummary> getJobSummaries() {
        if (jobSummaries == null) {
            this.jobSummaries = jobService.getJobSummaries();
        }
        return jobSummaries;
    }

}
