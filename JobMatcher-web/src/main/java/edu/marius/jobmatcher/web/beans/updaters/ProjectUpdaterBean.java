/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans.updaters;

import edu.marius.graph.entity.ProjectType;
import edu.marius.graph.entity.ProjectsType;
import edu.marius.jobmatcher.web.util.JsfUtils;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("projectUpdaterBean")
@RequestScoped
public class ProjectUpdaterBean {

    private String title;
    private String description;
    private String startDate;
    private String endDate;

    public void removeProject(ProjectType project) {
        JsfUtils.getCv().ifPresent(cv -> cv.getProjects().getProject().remove(project));
    }

    public void addProject() {
        JsfUtils.getCv().ifPresent(cv -> {
            if (cv.getProjects() == null) {
                cv.setProjects(new ProjectsType());
            }
            cv.getProjects().getProject().add(newProject());
        });
    }

    private ProjectType newProject() {
        ProjectType p = new ProjectType();
        p.setDescription(description);
        p.setTitle(title);
        p.setStartDate(startDate);
        p.setEndDate(endDate);
        return p;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
