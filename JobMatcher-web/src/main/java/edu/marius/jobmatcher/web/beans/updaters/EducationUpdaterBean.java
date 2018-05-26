/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans.updaters;

import edu.marius.graph.entity.EducationEntryType;
import edu.marius.graph.entity.EducationType;
import static edu.marius.jobmatcher.web.util.DateFormatter.format;
import edu.marius.jobmatcher.web.util.JsfUtils;
import java.util.Date;
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
    private Date startDate;
    private Date endDate;

    public void removeEducationEntry(EducationEntryType educationEntry) {
        JsfUtils.getCv().ifPresent(cv -> cv.getEducation().getEducationEntry().remove(educationEntry));
    }

    public void addEducationEntry() {
        JsfUtils.getCv().ifPresent(cv -> {
            if (cv.getEducation() == null) {
                cv.setEducation(new EducationType());
            }
            cv.getEducation().getEducationEntry().add(newEducationEntry());
        });
    }

    private EducationEntryType newEducationEntry() {
        EducationEntryType e = new EducationEntryType();
        e.setInstitution(institution);
        e.setTitle(title);
        e.setGrade(Float.parseFloat(grade));
        e.setStartDate(format(startDate));
        e.setEndDate(format(endDate));
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

}
