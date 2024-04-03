package soa.soa.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soa.soa.entity.Registrovanikorisnik;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrovanikorisnikRepository extends Neo4jRepository<Registrovanikorisnik, Long> {

    boolean existsRegistrovanikorisnikByKorisnickoimeOrLozinkaOrEmail(String korisnickoime, String lozinka, String email);

    Registrovanikorisnik findByKorisnickoimeAndLozinka(String korisnickoime, String lozinka);

    List<Registrovanikorisnik> findByAktivan(boolean aktivan);

    @Query("MATCH (r:Registrovanikorisnik) WHERE id(r) =$id RETURN r")
    Registrovanikorisnik findbyid(@Param("id")Long id);
}

