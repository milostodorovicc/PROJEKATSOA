package soa.blog.entity;

import java.time.LocalDate;

public class KomentarDTO {
    private String korisnickoime;
    private LocalDate datum;
    private String tekst;


    public KomentarDTO() {
    }


    public KomentarDTO(String korisnickoime, LocalDate datum, String tekst) {
        this.korisnickoime = korisnickoime;
        this.datum = datum;
        this.tekst = tekst;
    }


    public String getKorisnickoime() {
        return korisnickoime;
    }

    public void setKorisnickoime(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }



    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
}
