/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.matchers;

import edu.marius.graph.domain.cv.Cv;
import edu.marius.graph.domain.job.JobDescription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marius
 */
@Component
public class JobDescriptionComparator {

    @Autowired
    SimilarityCalculator similarityCalculator;

    public List<JobDescription> sort(List<JobDescription> jds, Cv cv) {
        List<JobDescription> sorted = new ArrayList<>(jds.size());
        List<JobDescriptionScore> jdScores = new ArrayList<>(jds.size());
        jds.forEach(jd -> {
            jdScores.add(new JobDescriptionScore(similarityCalculator.calculate(jd, cv), jd));
        });
        Collections.sort(jdScores);
        int cnt = 0;
        for (JobDescriptionScore jdScore : jdScores) {
            sorted.add(jdScore.jd);
            cnt++;
            if (cnt > 38) {
                break;
            }
        }
        return sorted;
    }

    private class JobDescriptionScore implements Comparable<JobDescriptionScore> {

        public JobDescriptionScore(int score, JobDescription jd) {
            this.score = score;
            this.jd = jd;
        }

        int score;
        JobDescription jd;

        @Override
        public int compareTo(JobDescriptionScore o) {
            return Integer.compare(o.score, this.score);
        }

    }
}
