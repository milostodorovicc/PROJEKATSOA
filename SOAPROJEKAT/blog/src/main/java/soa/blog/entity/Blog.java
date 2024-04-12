package soa.blog.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
import java.util.Set;

@Node
public class Blog {

    @Id
    @GeneratedValue
    private Long id;

    private String naslov;

    private String opis;

    private LocalDateTime datum;

    @Enumerated(EnumType.STRING)
    private Status status;


    @Relationship(type = "IMA_KOMENTARE")
    private Set<Komentar> komentari;


    public Blog(Long id, String naslov, String opis, LocalDateTime datum, Status status) {
        this.id = id;
        this.naslov = naslov;
        this.opis = opis;
        this.datum = datum;
        this.status = status;
    }


    public Blog() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }
}
