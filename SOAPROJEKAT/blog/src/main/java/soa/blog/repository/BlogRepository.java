package soa.blog.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soa.blog.entity.Blog;

@Repository
public interface BlogRepository extends Neo4jRepository<Blog,Long> {


    @Query("MATCH (b:Blog) WHERE id(b) =$id RETURN b")
    Blog findbyid(@Param("id")Long id);


}
