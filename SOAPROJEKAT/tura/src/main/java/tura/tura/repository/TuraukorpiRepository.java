package tura.tura.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import tura.tura.entity.Tura;
import tura.tura.entity.Turaukorpi;

import java.util.List;
import java.util.Set;

public interface TuraukorpiRepository extends Neo4jRepository<Turaukorpi,Long> {

    @Query("MATCH (k:Korpa)-[:SADRZI_TURE]->(t:Turaukorpi) WHERE id(k) =$id RETURN t")
    List<Turaukorpi> findbykorpaid(@Param("id")Long id);

    @Query("MATCH (t:Turaukorpi) WHERE id(t) =$id RETURN t")
    Turaukorpi findbyidukorpi(@Param("id")Long id);

    @Query("MATCH (k:Korpa)-[:SADRZI_TURE]->(t:Turaukorpi) WHERE id(k) =$idk and id(t)=$idt detach  delete t")
    void deleteturuukorpi(@Param("idk")Long idkorpe,@Param("idt")Long idtureukorpi);
}
