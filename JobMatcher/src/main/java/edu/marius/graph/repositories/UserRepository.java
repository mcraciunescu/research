/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.repositories;

import edu.marius.graph.domain.user.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marius
 */
@Repository
public interface UserRepository extends GraphRepository<User> {

    @Query("MATCH (n:User) WHERE n.name = {0} RETURN DISTINCT n")
    User findByName(String name);

    @Query("MATCH (n:User) WHERE ID(n) = {0} SET n.cvId = {1}")
    User assignCvId(Long userId, Long cvId);

    @Query("MATCH (n:User)  WHERE ID(n) = {0} REMOVE n.cvId")
    User removeCvId(Long userId);
}
