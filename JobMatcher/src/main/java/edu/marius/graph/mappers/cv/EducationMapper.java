/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.cv;

import edu.marius.graph.domain.cv.EducationEntry;
import edu.marius.graph.entity.EducationEntryType;
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
public class EducationMapper {

    private static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy"));

    public EducationEntry map(EducationEntryType educationType) {
        EducationEntry education = new EducationEntry();
        education.setInstitution(educationType.getInstitution());
        education.setTitle(educationType.getTitle());
        education.setGrade(new Double(educationType.getGrade()));
        try {
            education.setStartDate(sdf.get().parse(educationType.getStartDate()));
            education.setEndDate(sdf.get().parse(educationType.getEndDate()));
        } catch (ParseException ex) {
            Logger.getLogger(EducationMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return education;
    }

    public EducationEntryType map(EducationEntry ed) {
        EducationEntryType edType = new EducationEntryType();
        edType.setInstitution(ed.getInstitution());
        edType.setGrade(ed.getGrade().floatValue());
        edType.setTitle(ed.getTitle());
        edType.setStartDate(sdf.get().format(ed.getStartDate()));
        edType.setEndDate(sdf.get().format(ed.getEndDate()));
        return edType;
    }
}
