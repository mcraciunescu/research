/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.cv;

import edu.marius.graph.domain.cv.Course;
import edu.marius.graph.entity.CourseType;
import edu.marius.graph.entity.CoursesType;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marius
 */
@Service
public class CourseMapper {

    public Course map(CourseType course) {
        Course c = new Course();
        c.setInstitution(course.getInstitution());
        c.setTitle(course.getTitle());
        c.setStartDate(course.getStartDate());
        c.setEndDate(course.getEndDate());
        return c;
    }

    public CourseType map(Course course) {
        CourseType c = new CourseType();
        c.setInstitution(course.getInstitution());
        c.setTitle(course.getTitle());
        c.setStartDate(course.getStartDate());
        c.setEndDate(course.getEndDate());
        return c;
    }
}
