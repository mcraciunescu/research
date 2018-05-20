/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans;

import edu.marius.graph.entity.CvType;
import edu.marius.graph.entity.EducationEntryType;
import edu.marius.graph.entity.JobType;
import edu.marius.graph.entity.ProjectType;
import edu.marius.jobmatcher.web.service.CvService;
import edu.marius.jobmatcher.web.util.JsfUtils;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("updateBean")
@RequestScoped
public class UpdateBean {

    //education
    private String educationInstitution;
    private String educationTitle;
    private String educationGrade;
    private String educationStartDate;
    private String educationEndDate;

    //projects
    private String projectTitle;
    private String projectDescription;
    private String projectStartDate;
    private String projectEndDate;

    //skills
    private String skill;

    //languages
    private String language;

    @EJB
    CvService cvService;

    public void updateCv(CvType cv, Long userId, Long oldCvId) {
        Long newCvId = cvService.updateCv(cv, userId, oldCvId);
        JsfUtils.getUser().ifPresent(u -> u.setCvId(newCvId));
    }

    public void removeSkill(String skill) {
        JsfUtils.getCv().ifPresent(cv -> cv.getSkills().getSkill().remove(skill));
    }

    public void addSkill() {
        JsfUtils.getCv().ifPresent(cv -> cv.getSkills().getSkill().add(skill));
    }

    public void removeLanguage(String language) {
        JsfUtils.getCv().ifPresent(cv -> cv.getLanguages().getLanguage().remove(language));
    }

    public void addLanguage(String language) {
        JsfUtils.getCv().ifPresent(cv -> cv.getLanguages().getLanguage().add(language));
    }

    public void removeProject(ProjectType project) {
        JsfUtils.getCv().ifPresent(cv -> cv.getProjects().getProject().remove(project));
    }

    public void addProject(ProjectType project) {
        JsfUtils.getCv().ifPresent(cv -> cv.getProjects().getProject().add(project));
    }

    public void removeEducationEntry(EducationEntryType educationEntry) {
        JsfUtils.getCv().ifPresent(cv -> cv.getEducation().getEducationEntry().remove(educationEntry));
    }

    public void addEducationEntry(EducationEntryType educationEntry) {
        JsfUtils.getCv().ifPresent(cv -> cv.getEducation().getEducationEntry().add(educationEntry));
    }

    public void removeJob(JobType job) {
        JsfUtils.getCv().ifPresent(cv -> cv.getExperience().getJob().remove(job));
    }

    public void addJob(JobType job) {
        JsfUtils.getCv().ifPresent(cv -> cv.getExperience().getJob().add(job));
    }

    public String getEducationInstitution() {
        return educationInstitution;
    }

    public void setEducationInstitution(String educationInstitution) {
        this.educationInstitution = educationInstitution;
    }

    public String getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(String educationTitle) {
        this.educationTitle = educationTitle;
    }

    public String getEducationGrade() {
        return educationGrade;
    }

    public void setEducationGrade(String educationGrade) {
        this.educationGrade = educationGrade;
    }

    public String getEducationStartDate() {
        return educationStartDate;
    }

    public void setEducationStartDate(String educationStartDate) {
        this.educationStartDate = educationStartDate;
    }

    public String getEducationEndDate() {
        return educationEndDate;
    }

    public void setEducationEndDate(String educationEndDate) {
        this.educationEndDate = educationEndDate;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public CvService getCvService() {
        return cvService;
    }

    public void setCvService(CvService cvService) {
        this.cvService = cvService;
    }

}
