/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.cv;

import edu.marius.graph.domain.cv.Job;
import edu.marius.graph.entity.JobType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MariusCraciunescu
 */
@Service
@Transactional
public class JobMapper {

    private static final ThreadLocal<SimpleDateFormat> SDF = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy"));

    public Job map(JobType jobType) {
        Job job = new Job();
        job.setCompany(jobType.getCompany());
        job.setCountry(jobType.getCountry());
        job.setIndustry(jobType.getIndustry().toUpperCase());
        job.setDescription(jobType.getDescription());
        job.setTitle(jobType.getTitle());
        try {
            job.setStartDate(SDF.get().parse(jobType.getStartDate()));
            job.setEndDate(SDF.get().parse(jobType.getStartDate()));
        } catch (ParseException ex) {
            Logger.getLogger(JobMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return job;
    }

    public JobType map(Job job) {
        JobType j = new JobType();
        j.setCompany(job.getCompany());
        j.setCountry(job.getCountry());
        j.setTitle(job.getTitle());
        j.setIndustry(job.getIndustry());
        j.setStartDate(SDF.get().format(job.getStartDate()));
        j.setEndDate(SDF.get().format(job.getEndDate()));
        return j;
    }

}
