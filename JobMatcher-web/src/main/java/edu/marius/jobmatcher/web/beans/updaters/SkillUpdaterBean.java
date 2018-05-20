/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans.updaters;

import edu.marius.graph.entity.SkillsType;
import edu.marius.jobmatcher.web.util.JsfUtils;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("skillUpdaterBean")
@RequestScoped
public class SkillUpdaterBean {

    private String skill;

    public void removeSkill(String skill) {
        JsfUtils.getCv().ifPresent(cv -> cv.getSkills().getSkill().remove(skill));
    }

    public void addSkill() {
        JsfUtils.getCv().ifPresent(cv -> {
            if (cv.getSkills() == null) {
                cv.setSkills(new SkillsType());
            }
            cv.getSkills().getSkill().add(skill);
        });
        skill = null;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

}
