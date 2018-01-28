/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.service;

import edu.marius.graph.entity.JobPosting;
import edu.marius.graph.entity.JobSummary;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marius
 */
@Singleton
public class JobService {

    private static final String GET_JOB_SUMMARIES_URL = "http://localhost:8080/get/jobs";
    private static final String GET_MATCHING_JOBS = "http://localhost:8080/get/matchingJobs";
    private static final String GET_JOB_BY_ID = "http://localhost:8080/get/job";

    private Client client;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
    }

    public List<JobSummary> getJobSummaries() {
        return client.target(GET_JOB_SUMMARIES_URL)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<JobSummary>>() {
                });
    }

    public List<JobSummary> getMatchingJobs(long userId) {
        return client.target(GET_MATCHING_JOBS)
                .queryParam("id", userId)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<JobSummary>>() {
                });
    }

    public JobPosting getJobById(long jobId) {
        return client.target(GET_JOB_BY_ID)
                .queryParam("id", jobId)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get(JobPosting.class);
    }
}
