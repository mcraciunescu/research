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
    private LanguageMapper languageMapper;

    public Cv map(CvType cvType) {
        Cv cv = new Cv();
        cv.setFirstName(cvType.getFirstName());
        cv.setLastName(cvType.getLastName());
        cv.setContactNo("" + cvType.getContactNo());
        cv.seteMail(cvType.getEmail());
        cv.setSkills(skillMapper.map(cvType.getSkills()));
        cv.setAddress(addressMapper.map(cvType.getAddress()));
        cv.setJobs(
                cvType.getExperience()
                        .getJob()
                        .stream()
                        .map(jobType -> jobMapper.map(jobType))
                        .collect(Collectors.toList())
        );
        cv.setEducationEntries(
                cvType.getEducation()
                        .getEducationEntry()
                        .stream()
                        .map(educationType -> educationMapper.map(educationType))
                        .collect(Collectors.toList())
        );
        cv.setCourses(
                cvType.getCourses()
                        .getCourse()
                        .stream()
                        .map(courseMapper::map)
                        .collect(Collectors.toList())
        );
        cv.setLanguages(languageMapper.map(cvType.getLanguages()));
        return cv;
    }

    public CvType map(Cv cv) {
        CvType cvType = new CvType();
        cvType.setFirstName(cv.getFirstName());
        cvType.setLastName(cv.getLastName());
        cvType.setContactNo(Integer.parseInt(cv.getContactNo()));
        cvType.setEmail(cv.geteMail());
        cvType.setSkills(skillMapper.map(cv.getSkills()));
        cvType.setAddress(addressMapper.map(cv.getAddress()));

        ExperienceType experience = new ExperienceType();
        cv.getJobs().forEach(job -> experience.getJob().add(jobMapper.map(job)));

        cvType.setLanguages(languageMapper.map(cv.getLanguages()));

        EducationType education = new EducationType();
        cv.getEducationEntries().forEach(ed -> education.getEducationEntry().add(educationMapper.map(ed)));
        cvType.setEducation(education);

        if (cv.getCourses() != null) {
            CoursesType courses = new CoursesType();
            cv.getCourses().forEach(c -> courses.getCourse().add(courseMapper.map(c)));
            cvType.setCourses(courses);
        }
        cvType.setExperience(experience);
        return cvType;
    }
}
