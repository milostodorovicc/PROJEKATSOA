package tura.tura.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Turaukorpi {

    @Id
    @GeneratedValue
    private Long id;


    private Long idture;

    private int brojljudi;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdture() {
        return idture;
    }

    public void setIdture(Long idture) {
        this.idture = idture;
    }

    public int getBrojljudi() {
        return brojljudi;
    }

    public void setBrojljudi(int brojljudi) {
        this.brojljudi = brojljudi;
    }

    public Turaukorpi() {
    }

    public Turaukorpi(Long id, Long idture, int brojljudi) {
        this.id = id;
        this.idture = idture;
        this.brojljudi = brojljudi;
    }
}
