/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.services;

import edu.marius.graph.domain.user.User;
import edu.marius.graph.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marius
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(long id) {
        return userRepository.findOne(id);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public void assignCvIdToUser(Long userId, Long cvId) {
        userRepository.removeCvId(userId);
        userRepository.assignCvId(userId, cvId);
    }
}
