/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans;

import edu.marius.graph.entity.JobPosting;
import edu.marius.graph.entity.JobType;
import edu.marius.jobmatcher.web.service.JobService;
import static edu.marius.jobmatcher.web.util.JsfUtils.getSession;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("jobDetailsView")
@RequestScoped
public class JobDetailsViewBean {

    private Long jobId;
    private JobPosting job;

    @EJB
    JobService jobService;

    @PostConstruct
    public void init() {
        System.out.println("jobId: " + jobId);
        this.jobId = (Long) getSession().getAttribute("jobId");
        this.job = jobService.getJobById(this.jobId);
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public JobPosting getJob() {
        return job;
    }

    public void setJob(JobPosting job) {
        this.job = job;
    }

}
