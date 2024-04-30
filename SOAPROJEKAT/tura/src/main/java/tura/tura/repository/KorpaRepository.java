package tura.tura.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tura.tura.entity.Korpa;
import tura.tura.entity.Tura;
@Repository
public interface KorpaRepository extends Neo4jRepository<Korpa,Long> {

    @Query("MATCH (k:Korpa) WHERE id(k) =$id RETURN k")
    Korpa findbyid(@Param("id")Long id);
}
