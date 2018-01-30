/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.services;

import edu.marius.graph.domain.cv.Cv;
import edu.marius.graph.domain.job.JobDescription;
import edu.marius.graph.entity.JobSummary;
import edu.marius.graph.matchers.JobDescriptionComparator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 *
 * @author Marius
 */
@Service
public class MatcherService {

    @Autowired
    CvService cvService;

    @Autowired
    JobDescriptionService jobDescriptionService;

    @Autowired
    WordService wordService;

    @Autowired
    JobSummaryService jobSummaryService;

    @Autowired
    JobDescriptionComparator jdComparator;

    public List<JobSummary> getMatchingJobsSummaries(Long cvId) {
        return this.getMatchingJobs(cvId)
                .stream()
                .map(jobSummaryService::toSummary)
                .collect(Collectors.toList());
    }

    public List<JobDescription> getMatchingJobs(Long cvId) {
        Assert.notNull(cvId, "Id must be not null.");
        Cv cv = cvService.findById(cvId);
        List<String> industries = getCvIndustries(cv);
        industries.addAll(getSynonymIndustries(industries));
        List<JobDescription> candidates = new ArrayList<>();
        industries.forEach(i -> candidates.addAll(getJobsByIndustry(i)));
        //return candidates;
        return jdComparator.sort(candidates, cv);
    }

    private List<String> getSynonymIndustries(List<String> industries) {
        List<String> synonyms = new ArrayList<>();
        industries.forEach((industry) -> {
            synonyms.addAll(wordService.getSynonyms(industry));
        });
        return synonyms;
    }

    private List<String> getCvIndustries(Cv cv) {
        return cv.getJobs()
                .stream()
                .map(job -> job.getIndustry())
                .distinct()
                .collect(Collectors.toList());
    }

    private List<JobDescription> getJobsByIndustry(String industry) {
        return jobDescriptionService.findByIndustry(industry);
    }
}
