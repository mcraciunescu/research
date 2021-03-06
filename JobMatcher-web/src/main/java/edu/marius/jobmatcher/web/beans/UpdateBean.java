/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans;

import edu.marius.graph.entity.CvType;
import edu.marius.graph.entity.EducationEntryType;
import edu.marius.graph.entity.JobType;
import edu.marius.graph.entity.ProjectType;
import edu.marius.jobmatcher.web.service.CvService;
import edu.marius.jobmatcher.web.util.JsfUtils;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("updateBean")
@RequestScoped
public class UpdateBean {

    @EJB
    CvService cvService;

    public void updateCv(CvType cv, Long userId, Long oldCvId) {
        Long newCvId = cvService.updateCv(cv, userId, oldCvId);
        JsfUtils.getUser().ifPresent(u -> u.setCvId(newCvId));
    }

    public CvService getCvService() {
        return cvService;
    }

    public void setCvService(CvService cvService) {
        this.cvService = cvService;
    }

}
