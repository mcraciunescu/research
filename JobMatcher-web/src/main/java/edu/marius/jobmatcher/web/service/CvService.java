/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.marius.graph.entity.CvType;
import edu.marius.graph.entity.UserType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marius
 */
@Singleton
@Startup
public class CvService {

    private static final String GET_CV_URL = "http://localhost:8080/get/cv";
    private static final String CREATE_CV_URL = "http://localhost:8080/create/cv";
    private static final String UPDATE_CV_URL = "http://localhost:8080/update/cv";

    private Client client;

    private static final ThreadLocal<ObjectMapper> MAPPER = ThreadLocal.withInitial(ObjectMapper::new);

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
    }

    public CvType getCv(Long id) {
        try {
            return MAPPER.get().readValue(
                    client.target(GET_CV_URL)
                            .queryParam("id", id)
                            .request(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .get(String.class), CvType.class);
        } catch (IOException ex) {
            Logger.getLogger(CvService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Long updateCv(CvType newCv, Long userId, Long oldCvId) {
        try {
            String newCvId = client.target(UPDATE_CV_URL)
                    .queryParam("userId", userId)
                    .queryParam("cvId", oldCvId)
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.json(MAPPER.get().writeValueAsString(newCv)), String.class);
            return Long.parseLong(newCvId);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CvService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oldCvId;
    }
}
