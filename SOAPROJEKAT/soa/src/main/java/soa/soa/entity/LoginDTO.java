package soa.soa.entity;

public class LoginDTO {

    private Long id;
    private Uloga uloga;
    private boolean aktivan;
    private String korisnickoime;
    private String lozinka;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
