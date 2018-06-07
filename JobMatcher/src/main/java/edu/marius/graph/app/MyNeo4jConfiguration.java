package edu.marius.graph.app;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Import(RepositoryRestMvcConfiguration.class)
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan(basePackages = {"edu.marius.graph.services", "edu.marius.graph.mappers", "edu.marius.graph.matchers"})
@Configuration
@EnableNeo4jRepositories(basePackages = "edu.marius.graph.repositories")
public class MyNeo4jConfiguration extends Neo4jConfiguration {

    public static final String URL = "http://localhost:7474";

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI(URL);
        return config;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(
                getConfiguration(),
                new String[]{
                    "edu.marius.graph.domain"
                });
    }
}
