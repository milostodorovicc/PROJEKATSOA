package soa.blog.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soa.blog.entity.Blog;
import soa.blog.entity.Komentar;

import java.util.Set;

@Repository
public interface KomentarRepository extends Neo4jRepository<Komentar,Long> {

    @Query("MATCH (b:Blog)-[:IMA_KOMENTARE]->(k:Komentar) WHERE id(b) =$id RETURN k")
    Set<Komentar> findbyblogid(@Param("id")Long id);
}
