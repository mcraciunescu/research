/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.matchers;

import edu.marius.graph.domain.cv.Cv;
import edu.marius.graph.domain.cv.Job;
import edu.marius.graph.domain.cv.Skill;
import edu.marius.graph.domain.job.JobDescription;
import edu.marius.graph.domain.job.RequiredSkill;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marius
 */
@Component
public class SimilarityCalculator {

    private static final Pattern PATTERN = Pattern.compile("\\W+");

    public int calculate(JobDescription jd, Cv cv) {
        int similarity = 0;
        similarity += compareSkills(cv.getSkills(), jd.getSkills());
        similarity += compareTitle(cv.getJobs(), jd.getTitle());
        //jd.getExperienceRequirements();
        //jd.getEducationRequirements();
        //cv.getEducationEntries();
        //v.getLanguages();
        return similarity;
    }

    public int compareSkills(List<Skill> skills, List<RequiredSkill> reqSkills) {
        if (skills == null || reqSkills == null) {
            return 0;
        }
        int score = 0;

        for (Skill skill : skills) {
            for (RequiredSkill reqSkill : reqSkills) {
                score += countMatches(skill.getName(), reqSkill.getName());
            }
        }
        return score;
    }

    public int compareTitle(List<Job> jobs, String jobTitle) {
        if (jobs == null || jobTitle == null) {
            return 0;
        }
        int score = 0;
        for (Job job : jobs) {
            score += countMatches(job.getTitle(), jobTitle);
        }
        return score;
    }

    public int countMatches(String s1, String s2) {
        int matches = 0;
        String[] s1Parts = PATTERN.split(s1);
        String[] s2Parts = PATTERN.split(s2);
        for (String str1 : s1Parts) {
            for (String str2 : s2Parts) {
                if (str1.equals(str2)) {
                    matches++;
                }
            }
        }
        return matches;
    }

}
