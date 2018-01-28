/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.repositories;

import edu.marius.graph.domain.Word;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marius
 */
@Repository
public interface WordRepository extends GraphRepository<Word> {

    @Query("MATCH (w:Word)-[:IS_SYNONYM]-(synonyms) WHERE w.key = {0} RETURN synonyms")
    Iterable<Word> getFirstDegreeSynonyms(String word);
}
