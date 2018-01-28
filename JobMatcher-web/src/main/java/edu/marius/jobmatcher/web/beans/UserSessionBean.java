/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans;

import edu.marius.graph.entity.UserType;
import edu.marius.jobmatcher.web.util.JsfUtils;
import static edu.marius.jobmatcher.web.util.JsfUtils.getRootPath;
import static edu.marius.jobmatcher.web.util.JsfUtils.getSession;
import static edu.marius.jobmatcher.web.util.JsfUtils.redirect;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named(value = "userSessionBean")
@SessionScoped
public class UserSessionBean implements Serializable {

    private UserType user;

    @PostConstruct
    public void init() {
        user = JsfUtils.getUser();
    }

    public UserType getUser() {
        return user;
    }

    public void logout() {
        getSession().invalidate();
        redirect(getRootPath());
    }
}
