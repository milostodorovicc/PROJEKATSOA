package tura.tura.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tura.tura.entity.Korpa;
import tura.tura.entity.Tura;
import tura.tura.entity.Turaukorpi;

import java.util.Set;

@Repository
public interface TuraRepository extends Neo4jRepository<Tura,Long> {

    @Query("MATCH (t:Tura) WHERE id(t) =$id RETURN t")
    Tura findbyid(@Param("id")Long id);





}
