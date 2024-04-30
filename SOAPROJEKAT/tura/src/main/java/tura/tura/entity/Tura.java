package tura.tura.entity;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Node
public class Tura {

    @Id
    @GeneratedValue
    private Long id;

    private String naziv;

    private String opis;

    private int duzina;

    private int tezina;

    private int cena;

    private Long idkreatorature;

    private List<String> tagovi;

    private int brojljudi;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getDuzina() {
        return duzina;
    }

    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }

    public int getTezina() {
        return tezina;
    }

    public void setTezina(int tezina) {
        this.tezina = tezina;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }


    public Long getIdkreatorature() {
        return idkreatorature;
    }

    public void setIdkreatorature(Long idkreatorature) {
        this.idkreatorature = idkreatorature;
    }

    public List<String> getTagovi() {
        return tagovi;
    }

    public void setTagovi(List<String> tagovi) {
        this.tagovi = tagovi;
    }

    public int getBrojljudi() {
        return brojljudi;
    }

    public void setBrojljudi(int brojljudi) {
        this.brojljudi = brojljudi;
    }

    public Tura() {
    }

    public Tura(Long id, String naziv, String opis, int duzina, int tezina, int cena, Long idkreatorature, List<String> tagovi, int brojljudi) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.duzina = duzina;
        this.tezina = tezina;
        this.cena = cena;
        this.idkreatorature = idkreatorature;
        this.tagovi = tagovi;
        this.brojljudi = brojljudi;
    }
}
