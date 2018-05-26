/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans.updaters;

import edu.marius.graph.entity.ExperienceType;
import edu.marius.graph.entity.JobType;
import static edu.marius.jobmatcher.web.util.DateFormatter.format;
import edu.marius.jobmatcher.web.util.JsfUtils;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("jobUpdaterBean")
@RequestScoped
public class JobUpdaterBean {

    private String title;
    private String company;
    private String industry;
    private String country;
    private Date startDate;
    private Date endDate;
    private String description;

    public void removeJob(JobType job) {
        JsfUtils.getCv().ifPresent(cv -> cv.getExperience().getJob().remove(job));
    }

    public void addJob() {
        JsfUtils.getCv().ifPresent(cv -> {
            if (cv.getExperience() == null) {
                cv.setExperience(new ExperienceType());
            }
            cv.getExperience().getJob().add(newJob());
        });
    }

    private JobType newJob() {
        JobType j = new JobType();
        j.setTitle(title);
        j.setIndustry(industry);
        j.setCompany(company);
        j.setCountry(country);
        j.setDescription(description);
        j.setStartDate(format(startDate));
        j.setEndDate(format(endDate));
        return j;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
