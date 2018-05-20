/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.cv;

import edu.marius.graph.domain.cv.Cv;
import edu.marius.graph.entity.CoursesType;
import edu.marius.graph.entity.CvType;
import edu.marius.graph.entity.EducationType;
import edu.marius.graph.entity.ExperienceType;
import edu.marius.graph.entity.ProjectsType;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MariusCraciunescu
 */
@Service
@Transactional
public class CvMapper {

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private EducationMapper educationMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private LanguageMapper languageMapper;

    public Cv map(CvType cvType) {
        Cv cv = new Cv();
        cv.setFirstName(cvType.getFirstName());
        cv.setLastName(cvType.getLastName());
        cv.setContactNo(cvType.getContactNo());
        cv.seteMail(cvType.getEmail());
        cv.setSkills(skillMapper.map(cvType.getSkills()));
        cv.setAddress(addressMapper.map(cvType.getAddress()));
        if (cvType.getExperience() != null) {
            cv.setJobs(
                    cvType.getExperience()
                            .getJob()
                            .stream()
                            .map(jobType -> jobMapper.map(jobType))
                            .collect(Collectors.toList())
            );
        }
        if (cvType.getEducation() != null) {
            cv.setEducationEntries(
                    cvType.getEducation()
                            .getEducationEntry()
                            .stream()
                            .map(educationType -> educationMapper.map(educationType))
                            .collect(Collectors.toList())
            );
        }
        if (cvType.getCourses() != null) {
            cv.setCourses(
                    cvType.getCourses()
                            .getCourse()
                            .stream()
                            .map(courseMapper::map)
                            .collect(Collectors.toList())
            );
        }
        if (cvType.getProjects() != null) {
            cv.setProjects(
                    cvType.getProjects()
                            .getProject()
                            .stream()
                            .map(projectMapper::map)
                            .collect(Collectors.toList())
            );
        }
        cv.setLanguages(languageMapper.map(cvType.getLanguages()));
        return cv;
    }

    public CvType map(Cv cv) {
        CvType cvType = new CvType();
        cvType.setFirstName(cv.getFirstName());
        cvType.setLastName(cv.getLastName());
        cvType.setContactNo(cv.getContactNo());
        cvType.setEmail(cv.geteMail());
        cvType.setSkills(skillMapper.map(cv.getSkills()));
        cvType.setAddress(addressMapper.map(cv.getAddress()));

        if (cv.getJobs() != null) {
            ExperienceType experience = new ExperienceType();
            cv.getJobs().forEach(job -> experience.getJob().add(jobMapper.map(job)));
            cvType.setExperience(experience);
        }

        cvType.setLanguages(languageMapper.map(cv.getLanguages()));

        if (cv.getEducationEntries() != null) {
            EducationType education = new EducationType();
            cv.getEducationEntries().forEach(ed -> education.getEducationEntry().add(educationMapper.map(ed)));
            cvType.setEducation(education);
        }

        if (cv.getCourses() != null) {
            CoursesType courses = new CoursesType();
            cv.getCourses().forEach(c -> courses.getCourse().add(courseMapper.map(c)));
            cvType.setCourses(courses);
        }
        if (cv.getProjects() != null) {
            ProjectsType projects = new ProjectsType();
            cv.getProjects().forEach(p -> projects.getProject().add(projectMapper.map(p)));
            cvType.setProjects(projects);
        }
        return cvType;
    }
}
