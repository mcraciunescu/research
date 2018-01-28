/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans;

import edu.marius.graph.entity.CvType;
import edu.marius.graph.entity.UserType;
import edu.marius.jobmatcher.web.service.UserService;
import edu.marius.jobmatcher.web.util.JsfUtils;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("cvBean")
@RequestScoped
public class CvBean {

    @EJB
    private UserService userService;

    private CvType cv;
    private Long cvId;

    public void assignCv() {
        userService.assignCvToUser(JsfUtils.getUser());
    }

    public Long getCvId() {
        return cvId;
    }

    public void setCvId(Long cvId) {
        this.cvId = cvId;
    }

    public CvType getCv() {
        return cv;
    }

    public void setCv(CvType cv) {
        this.cv = cv;
    }

}
