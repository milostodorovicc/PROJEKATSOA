package soa.blog.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Node
public class Komentar {

    @Id
    @GeneratedValue
    private Long id;

    private String tekst;

    private LocalDate datumkreiranja;

    private LocalDate datumposlednjeizmene;

    private Long idkorisnika;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDate getDatumkreiranja() {
        return datumkreiranja;
    }

    public void setDatumkreiranja(LocalDate datumkreiranja) {
        this.datumkreiranja = datumkreiranja;
    }

    public LocalDate getDatumposlednjeizmene() {
        return datumposlednjeizmene;
    }

    public void setDatumposlednjeizmene(LocalDate datumposlednjeizmene) {
        this.datumposlednjeizmene = datumposlednjeizmene;
    }

    public Long getIdkorisnika() {
        return idkorisnika;
    }

    public void setIdkorisnika(Long idkorisnika) {
        this.idkorisnika = idkorisnika;
    }


    public Komentar() {
    }


    public Komentar(Long id, String tekst, LocalDate datumkreiranja, LocalDate datumposlednjeizmene, Long idkorisnika) {
        this.id = id;
        this.tekst = tekst;
        this.datumkreiranja = datumkreiranja;
        this.datumposlednjeizmene = datumposlednjeizmene;
        this.idkorisnika = idkorisnika;
    }
}
