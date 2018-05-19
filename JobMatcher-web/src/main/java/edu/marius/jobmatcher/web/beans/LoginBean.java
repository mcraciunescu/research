/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans;

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
            session.setAttribute("cv", cvService.getCv(user.getCvId()));
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
}
