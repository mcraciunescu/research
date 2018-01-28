package edu.marius.graph.repositories;

import edu.marius.graph.domain.cv.Cv;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MariusCraciunescu
 */
@Repository
public interface CvRepository extends GraphRepository<Cv> {

}
