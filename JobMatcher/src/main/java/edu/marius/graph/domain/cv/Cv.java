package edu.marius.graph.domain.cv;

import org.neo4j.ogm.annotation.*;

import java.util.List;

@NodeEntity
public class Cv {

    @GraphId
    private Long id;

    private String firstName;
    private String lastName;
    private String contactNo;
    private String eMail;

    @Relationship(type = "LIVES")
    private Address address;

    @Relationship(type = "WORKED_AT")
    private List<Job> jobs;

    @Relationship(type = "LEARNED_AT")
    private List<EducationEntry> educationEntries;

    @Relationship(type = "PARTICIPATED_AT")
    private List<Course> courses;

    @Relationship(type = "DEVELOPED")
    private List<Project> projects;

    @Relationship(type = "KNOWS")
    private List<Skill> skills;

    @Relationship(type = "SPEAKS")
    private List<Language> languages;

    public Cv() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<EducationEntry> getEducationEntries() {
        return educationEntries;
    }

    public void setEducationEntries(List<EducationEntry> educationEntries) {
        this.educationEntries = educationEntries;
    }

    public void addEducationEntry(EducationEntry educationEntry) {
        educationEntries.add(educationEntry);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> language) {
        this.languages = language;
    }

    public void addLanguage(Language language) {
        languages.add(language);
    }
}
