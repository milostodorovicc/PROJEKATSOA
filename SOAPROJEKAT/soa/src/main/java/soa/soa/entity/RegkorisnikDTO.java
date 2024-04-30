package soa.soa.entity;

public class RegkorisnikDTO {

    private String korisnickoime;
    private String email;


    public RegkorisnikDTO() {
    }


    public RegkorisnikDTO(String korisnickoime, String email) {
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


}
