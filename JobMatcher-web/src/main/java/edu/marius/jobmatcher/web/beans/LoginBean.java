/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans;

import edu.marius.graph.entity.AddressType;
import edu.marius.graph.entity.CoursesType;
import edu.marius.graph.entity.CvType;
import edu.marius.graph.entity.EducationEntryType;
import edu.marius.graph.entity.EducationType;
import edu.marius.graph.entity.ExperienceType;
import edu.marius.graph.entity.LanguagesType;
import edu.marius.graph.entity.ProjectsType;
import edu.marius.graph.entity.SkillsType;
import edu.marius.graph.entity.UserType;
import edu.marius.jobmatcher.web.service.CvService;
import edu.marius.jobmatcher.web.service.UserService;
import edu.marius.jobmatcher.web.util.JsfUtils;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marius
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    @EJB
    UserService userService;

    @EJB
    CvService cvService;

    private String username;
    private String password;

    public String login() {
        UserType u = new UserType();
        u.setName(username);
        u.setPassword(password);
        UserType user = userService.login(u);
        if (user != null) {
            HttpSession session = JsfUtils.getSession();
            session.setAttribute("user", user);
            CvType cv = cvService.getCv(user.getCvId());
            ensureCvFields(cv);
            session.setAttribute("cv", cv);
            return "user/cv.xhtml";
        } else {
            //JSFUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Wrong credentials");
            //show err
        }
        return "";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void ensureCvFields(CvType cv) {
        if (cv.getAddress() == null) {
            cv.setAddress(new AddressType());
        }
//        if (cv.getCourses() == null) {
//            cv.setCourses(new CoursesType());
//        }
//        if (cv.getEducation() == null) {
//            cv.setEducation(new EducationType());
//        }
//        if (cv.getExperience() == null) {
//            cv.setExperience(new ExperienceType());
//        }
//        if (cv.getLanguages() == null) {
//            cv.setLanguages(new LanguagesType());
//        }
//        if (cv.getSkills() == null) {
//            cv.setSkills(new SkillsType());
//        }
//        if (cv.getProjects() == null) {
//            cv.setProjects(new ProjectsType());
//        }
    }
}
