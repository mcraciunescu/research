/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans.updaters;

import edu.marius.graph.entity.CourseType;
import edu.marius.graph.entity.CoursesType;
import edu.marius.jobmatcher.web.util.JsfUtils;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("courseUpdaterBean")
@RequestScoped
public class CourseUpdaterBean {

    private String institution;
    private String title;
    private String startDate;
    private String endDate;

    public void removeCourse(CourseType course) {
        JsfUtils.getCv().ifPresent(cv -> cv.getCourses().getCourse().remove(course));
    }

    public void addCourse() {
        JsfUtils.getCv().ifPresent(cv -> {
            if (cv.getCourses() == null) {
                cv.setCourses(new CoursesType());
            }
            cv.getCourses().getCourse().add(newCourse());
        });
    }

    private CourseType newCourse() {
        CourseType course = new CourseType();
        course.setEndDate(endDate);
        course.setStartDate(startDate);
        course.setInstitution(institution);
        course.setTitle(title);
        return course;
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
