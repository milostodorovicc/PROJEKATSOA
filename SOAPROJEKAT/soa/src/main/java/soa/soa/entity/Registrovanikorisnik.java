package soa.soa.entity;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Registrovanikorisnik {

    @Id
    @GeneratedValue
    private Long id;

    private String korisnickoime;

    private String lozinka;

    private String email;

    @Enumerated(EnumType.STRING)
    private Uloga uloga;

    private boolean aktivan;




    public Registrovanikorisnik() {
    }

    public Registrovanikorisnik(Long id, String korisnickoime, String lozinka, String email, Uloga uloga, boolean aktivan) {
        this.id = id;
        this.korisnickoime = korisnickoime;
        this.lozinka = lozinka;
        this.email = email;
        this.uloga = uloga;
        this.aktivan = aktivan;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoime() {
        return korisnickoime;
    }

    public void setKorisnickoime(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }


    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }
}
