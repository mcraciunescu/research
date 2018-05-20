/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.cv;

import edu.marius.graph.domain.cv.Skill;
import edu.marius.graph.entity.SkillsType;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MariusCraciunescu
 */
@Service
@Transactional
public class SkillMapper {

    public List<Skill> map(SkillsType skillsType) {
        if (skillsType == null) {
            return Collections.emptyList();
        }
        return skillsType.getSkill()
                .stream()
                .map(s -> new Skill(s))
                .collect(Collectors.toList());
    }

    public SkillsType map(List<Skill> skills) {
        if (skills == null) {
            return null;
        }
        SkillsType skillsType = new SkillsType();
        skills.forEach(skill -> skillsType.getSkill().add(skill.getName()));
        return skillsType;
    }
}
