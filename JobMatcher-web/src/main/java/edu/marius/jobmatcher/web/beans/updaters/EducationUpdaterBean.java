/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans.updaters;

import edu.marius.graph.entity.EducationEntryType;
import edu.marius.jobmatcher.web.util.JsfUtils;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("educationUpdaterBean")
@RequestScoped
public class EducationUpdaterBean {

    private String institution;
    private String title;
    private String grade;
    private String startDate;
    private String endDate;

    public void removeEducationEntry(EducationEntryType educationEntry) {
        JsfUtils.getCv().ifPresent(cv -> cv.getEducation().getEducationEntry().remove(educationEntry));
    }

    public void addEducationEntry() {
        JsfUtils.getCv().ifPresent(cv -> cv.getEducation().getEducationEntry().add(newEducationEntry()));
    }

    private EducationEntryType newEducationEntry() {
        EducationEntryType e = new EducationEntryType();
        e.setInstitution(institution);
        e.setTitle(title);
        e.setGrade(Float.parseFloat(grade));
        e.setStartDate(startDate);
        e.setEndDate(endDate);
        return e;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
