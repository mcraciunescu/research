/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.beans.updaters;

import edu.marius.graph.entity.CvType;
import edu.marius.jobmatcher.web.util.JsfUtils;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marius
 */
@Named("personalInfoUpdaterBean")
@RequestScoped
public class PersonalInfoUpdaterBean {

    private String firstName;
    private String lastName;
    private String contactNo;
    private String email;
    private String city;
    private String state;
    private String zip;

    @PostConstruct
    public void init() {
        JsfUtils.getCv().ifPresent(this::doInit);
    }

    public void editPersonalInformation() {
        JsfUtils.getCv().ifPresent(this::doEdit);
    }

    private void doInit(CvType cv) {
        firstName = cv.getFirstName();
        lastName = cv.getLastName();
        contactNo = cv.getContactNo();
        email = cv.getEmail();
        city = cv.getAddress().getCity();
        state = cv.getAddress().getState();
        zip = cv.getAddress().getZip();
    }

    private void doEdit(CvType cv) {
        cv.setFirstName(firstName);
        cv.setLastName(lastName);
        cv.setContactNo(contactNo);
        cv.setEmail(email);
        cv.getAddress().setCity(city);
        cv.getAddress().setState(state);
        cv.getAddress().setZip(zip);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
