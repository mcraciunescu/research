/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.job;

import edu.marius.graph.domain.job.Benefit;
import edu.marius.graph.domain.job.EducationRequirement;
import edu.marius.graph.domain.job.ExperienceRequirement;
import edu.marius.graph.domain.job.JobDescription;
import edu.marius.graph.domain.job.Responsibility;
import edu.marius.graph.domain.job.RequiredSkill;
import edu.marius.graph.entity.JobPosting;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MariusCraciunescu
 */
@Service
@Transactional
public class JobDescriptionMapper {

    private static final ThreadLocal<SimpleDateFormat> SDF = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy"));
    private static final Pattern DOT_PATTERN = Pattern.compile("•");
    private static final Pattern LINE_PATTERN = Pattern.compile("-");

    public JobDescription map(final JobPosting jobPosting) throws ParseException {
        final JobDescription jd = new JobDescription();
        jd.setAddress(jobPosting.getJobLocation().getAddress().getAddressLocality());
        jd.setCompany(Optional.ofNullable(jobPosting.getHiringOrganization())
                .orElse(new JobPosting.HiringOrganization()).getName()
        );
        jd.setDatePosted(SDF.get().parse(jobPosting.getDatePosted()));
        jd.setDescription(jobPosting.getDescription());
        jd.setEmploymentType(jobPosting.getEmploymentType());
        jd.setIndustry(jobPosting.getIndustry().toUpperCase());
        jd.setOcupationCategory(jobPosting.getOccupationalCategory());
        jd.setTitle(jobPosting.getTitle());

        jd.setBenefits(
                toList(jobPosting.getJobBenefits())
                        .stream()
                        .map(b -> new Benefit(b))
                        .collect(Collectors.toList())
        );
        jd.setEducationRequirements(
                toList(jobPosting.getEducationRequirements())
                        .stream()
                        .map(e -> new EducationRequirement(e))
                        .collect(Collectors.toList())
        );
        jd.setExperienceRequirements(
                toList(jobPosting.getExperienceRequirements())
                        .stream()
                        .map(e -> new ExperienceRequirement(e))
                        .collect(Collectors.toList())
        );
        //jd.setQualifications(toList(jobPosting.getQualifications()));

        jd.setResponsibilities(
                toList(jobPosting.getResponsibilities())
                        .stream()
                        .map(r -> new Responsibility(r))
                        .collect(Collectors.toList())
        );

        jd.setSkills(toList(jobPosting.getSkills())
                .stream()
                .map(skill -> new RequiredSkill(skill))
                .collect(Collectors.toList())
        );
        return jd;
    }

    public JobPosting map(JobDescription jd) {
        JobPosting jp = new JobPosting();
        jp.setTitle(jd.getTitle());
        jp.setDescription(jd.getDescription());
        jp.setDatePosted(SDF.get().format(jd.getDatePosted()));
        jp.setEmploymentType(jd.getEmploymentType());
        jp.setIndustry(jd.getIndustry());
        jp.setOccupationalCategory(jd.getOcupationCategory());

        JobPosting.HiringOrganization company = new JobPosting.HiringOrganization();
        company.setName(jd.getCompany());
        jp.setHiringOrganization(company);

        jp.setJobBenefits(
                asString(jd.getBenefits().stream().map(Benefit::getName).collect(Collectors.toList()))
        );

        jp.setEducationRequirements(
                asString(jd.getEducationRequirements().stream()
                        .map(EducationRequirement::getName).collect(Collectors.toList()))
        );

        jp.setExperienceRequirements(
                asString(jd.getExperienceRequirements().stream()
                        .map(ExperienceRequirement::getName).collect(Collectors.toList()))
        );

        jp.setResponsibilities(
                asString(jd.getResponsibilities().stream()
                        .map(Responsibility::getName).collect(Collectors.toList()))
        );

        jp.setSkills(
                asString(jd.getSkills().stream()
                        .map(RequiredSkill::getName).collect(Collectors.toList()))
        );
        return jp;
    }

    private List<String> toList(String text) {
        if (text == null) {
            return Collections.emptyList();
        }
        if (text.contains("•")) {
            return Arrays.asList(DOT_PATTERN.split(text));
        }
        if (text.contains("-")) {
            return Arrays.asList(LINE_PATTERN.split(text));
        }
        return Arrays.asList(text);
    }

    public String asString(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        strings.forEach((s) -> {
            sb.append(s).append("<br/>");
        });
        return sb.toString();
    }
}
