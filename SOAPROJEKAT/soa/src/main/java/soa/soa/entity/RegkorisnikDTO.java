package soa.soa.entity;

public class RegkorisnikDTO {

    private Long id;
    private String korisnickoime;
    private String email;


    public RegkorisnikDTO() {
    }


    public RegkorisnikDTO(Long id, String korisnickoime, String email) {
        this.id = id;
        this.korisnickoime = korisnickoime;
        this.email = email;

    }


    public String getKorisnickoime() {
        return korisnickoime;
    }

    public void setKorisnickoime(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
