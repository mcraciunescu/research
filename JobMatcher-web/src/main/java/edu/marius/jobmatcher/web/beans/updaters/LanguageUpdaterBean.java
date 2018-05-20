/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans.updaters;

import edu.marius.jobmatcher.web.util.JsfUtils;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("languageUpdaterBean")
@RequestScoped
public class LanguageUpdaterBean {

    private String language;

    public void removeLanguage(String language) {
        JsfUtils.getCv().ifPresent(cv -> cv.getLanguages().getLanguage().remove(language));
    }

    public void addLanguage() {
        JsfUtils.getCv().ifPresent(cv -> cv.getLanguages().getLanguage().add(language));
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
