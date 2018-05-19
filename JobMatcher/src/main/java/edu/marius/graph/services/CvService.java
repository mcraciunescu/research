/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.services;

import edu.marius.graph.domain.cv.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.marius.graph.repositories.CvRepository;

/**
 * @author MariusCraciunescu
 */
@Service
@Transactional
public class CvService {
    
    @Autowired
    private CvRepository repository;
    
    public Cv create(Cv cv) {
        return repository.save(cv);
    }
    
    public void delete(Cv cv) {
        repository.delete(cv);
    }
    
    public void deleteById(long id) {
        repository.delete(id);
    }
    
    public Cv findById(long id) {
        return repository.findOne(id);
    }
    
    public Iterable<Cv> findAll() {
        return repository.findAll();
    }
    
}
