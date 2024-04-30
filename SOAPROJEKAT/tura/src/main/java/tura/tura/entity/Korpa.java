package tura.tura.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

@Node
public class Korpa {

    @Id
    @GeneratedValue
    private Long id;



    @Relationship(type = "SADRZI_TURE")
    private List<Turaukorpi> ture;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Turaukorpi> getTure() {
        return ture;
    }

    public void setTure(List<Turaukorpi> ture) {
        this.ture = ture;
    }


    public Korpa(Long id, List<Turaukorpi> ture) {
        this.id = id;
        this.ture = ture;
    }


    public Korpa() {
    }
}
