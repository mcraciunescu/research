/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans;

import edu.marius.graph.entity.UserType;
import edu.marius.jobmatcher.web.service.UserService;
import static edu.marius.jobmatcher.web.util.JsfUtils.addMessage;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named(value = "registrationBean")
@RequestScoped
public class RegistrationBean {

    @EJB
    UserService userService;

    private String username;
    private String password;
    private String passwordVerified;

    public String register() {
        if (!Objects.equals(password, passwordVerified)) {
            addMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "The passwords are different.");
            return "";
        }

        UserType u = new UserType();
        u.setName(username);
        u.setPassword(password);

        String outcome = userService.create(u);
        if ("success".equals(outcome)) {
            return "index.xhtml";
        }
        addMessage(FacesMessage.SEVERITY_ERROR,
                "Error", "The user already exists.");
        return "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerified() {
        return passwordVerified;
    }

    public void setPasswordVerified(String passwordVerified) {
        this.passwordVerified = passwordVerified;
    }

}
