package edu.marius.graph.domain.job;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author MariusCraciunescu
 */
@NodeEntity
public class JobDescription {

    @GraphId
    private Long id;

    private String title;
    private String description;
    private String employmentType;
    private String ocupationCategory;
    private String company;
    private String address;
    private Date datePosted;

    @Relationship(type = "IN_DOMAIN")
    private String industry;

    @Relationship(type = "REQ_LEARNED_AT")
    private List<EducationRequirement> educationRequirements;

    @Relationship(type = "REQ_IS_EXPERIENCED")
    private List<ExperienceRequirement> experienceRequirements;

    @Relationship(type = "REQ_IS_QUALIFIED")
    private List<Qualification> qualifications;

    @Relationship(type = "REQ_DOES")
    private List<Responsibility> responsibilities;

    @Relationship(type = "OFFERS")
    private List<Benefit> benefits;

    @Relationship(type = "REQ_KNOWS")
    private List<RequiredSkill> skills;

    public JobDescription() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getOcupationCategory() {
        return ocupationCategory;
    }

    public void setOcupationCategory(String ocupationCategory) {
        this.ocupationCategory = ocupationCategory;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public List<EducationRequirement> getEducationRequirements() {
        return educationRequirements;
    }

    public void setEducationRequirements(List<EducationRequirement> educationRequirements) {
        this.educationRequirements = educationRequirements;
    }

    public List<ExperienceRequirement> getExperienceRequirements() {
        return experienceRequirements;
    }

    public void setExperienceRequirements(List<ExperienceRequirement> experienceRequirements) {
        this.experienceRequirements = experienceRequirements;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public List<Responsibility> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<Responsibility> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public List<RequiredSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<RequiredSkill> skills) {
        this.skills = skills;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JobDescription other = (JobDescription) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "JobDescription{" + "id=" + id + ", title=" + title + ", description=" + description + ", employmentType="
                + employmentType + ", ocupationCategory=" + ocupationCategory + ", company=" + company + ", industry=" + industry
                + ", address=" + address + ", datePosted=" + datePosted + ", educationRequirements=" + educationRequirements
                + ", experienceRequirements=" + experienceRequirements + ", qualifications=" + qualifications
                + ", responsibilities=" + responsibilities + ", benefits=" + benefits + ", skills=" + skills + '}';
    }

}
