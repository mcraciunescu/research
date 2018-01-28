/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans;

import edu.marius.graph.entity.JobSummary;
import edu.marius.jobmatcher.web.service.JobService;
import static edu.marius.jobmatcher.web.util.JsfUtils.getSession;
import static edu.marius.jobmatcher.web.util.JsfUtils.redirect;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("jobSummaryBean")
@SessionScoped
public class JobSummaryBean implements Serializable {

    @EJB
    private JobService jobService;

    private List<JobSummary> jobSummaries;

    @PostConstruct
    public void init() {
        this.jobSummaries = jobService.getJobSummaries();
    }

    public void view(Long id) {
        getSession().setAttribute("jobId", id);
        redirect("jobDetails.xhtml");
    }

    public List<JobSummary> getJobSummaries() {
        return jobSummaries;
    }

    public void setJobSummaries(List<JobSummary> jobSummaries) {
        this.jobSummaries = jobSummaries;
    }

}
