package soa.soa.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import soa.soa.entity.Registrovanikorisnik;
import soa.soa.entity.Role;
@Repository
public interface RoleRepository extends Neo4jRepository<Role, Long> {
}
