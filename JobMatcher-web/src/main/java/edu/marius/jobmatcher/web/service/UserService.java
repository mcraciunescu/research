/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.service;

import edu.marius.graph.entity.UserType;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marius
 */
@Singleton
public class UserService {

    private static final String GET_USER_URL = "http://localhost:8080/get/user";
    private static final String CREATE_USER_URL = "http://localhost:8080/create/user";

    private static final String ASSIGN_CV_TO_USER_URL = "http://localhost:8080/assignCv";

    private Client client;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
    }

    public UserType login(UserType u) {
        UserType user = getUser(u.getName());
        if (user != null && user.getPassword().equals(u.getPassword())) {
            return user;
        }
        return null;
    }

    public String create(UserType u) {
        return createUser(u);
    }

    private UserType getUser(String name) {
        return client.target(GET_USER_URL)
                .queryParam("name", name)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get(UserType.class);
    }

    private String createUser(UserType user) {
        return client.target(CREATE_USER_URL)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(user), String.class);
    }

    public void assignCvToUser(UserType user) {
        client.target(ASSIGN_CV_TO_USER_URL)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.json(user), String.class);
    }
}
