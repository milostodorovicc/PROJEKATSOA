package tura.tura.entity;

public class TuraDTO {

    private String naziv;
    private int cena;
    private int brojljudi;
    private Long id;


    public TuraDTO() {
    }

    public TuraDTO(String naziv, int cena, int brojljudi, Long id) {
        this.naziv = naziv;
        this.cena = cena;
        this.brojljudi = brojljudi;
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getBrojljudi() {
        return brojljudi;
    }

    public void setBrojljudi(int brojljudi) {
        this.brojljudi = brojljudi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
