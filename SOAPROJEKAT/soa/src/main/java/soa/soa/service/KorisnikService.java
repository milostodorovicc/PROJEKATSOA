package soa.soa.service;

import soa.soa.entity.LoginDTO;
import soa.soa.entity.Registrovanikorisnik;

import java.util.List;

public interface KorisnikService {

    Registrovanikorisnik createKorisnik(Registrovanikorisnik regkorisnik) throws Exception;

    LoginDTO proveri(String korisnickoime, String lozinka) throws Exception;

    List<Registrovanikorisnik> findAll(String uloga) throws Exception;

    Registrovanikorisnik update(Registrovanikorisnik regkorisnik, String uloga) throws Exception;
}
