/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.user;

import edu.marius.graph.domain.user.User;
import edu.marius.graph.entity.UserType;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marius
 */
@Component
public class UserMapper {

    public User map(UserType userType) {
        User u = new User();
        u.setName(userType.getName());
        u.setPassword(userType.getPassword());
        return u;
    }

    public UserType map(User user) {
        UserType u = new UserType();
        if (user == null) {
            return u;
        }
        if (user.getCvId() != null) {
            u.setCvId(user.getCvId());
        }
        u.setId(user.getId());
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        return u;
    }
}
