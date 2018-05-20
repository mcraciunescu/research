/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.cv;

import edu.marius.graph.domain.cv.Project;
import edu.marius.graph.entity.ProjectType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marius
 */
@Service
public class ProjectMapper {

    private static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy"));

    public Project map(ProjectType project) {
        Project p = new Project();
        p.setDescription(project.getDescription());
        p.setTitle(project.getTitle());
        try {
            p.setStartDate(sdf.get().parse(project.getStartDate()));
            p.setEndDate(sdf.get().parse(project.getEndDate()));
        } catch (ParseException ex) {
            Logger.getLogger(ProjectMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public ProjectType map(Project project) {
        ProjectType p = new ProjectType();
        p.setDescription(project.getDescription());
        p.setTitle(project.getTitle());
        if (project.getStartDate() != null) {
            p.setStartDate(sdf.get().format(project.getStartDate()));
        }
        if (project.getEndDate() != null) {
            p.setEndDate(sdf.get().format(project.getEndDate()));
        }
        return p;
    }
}
