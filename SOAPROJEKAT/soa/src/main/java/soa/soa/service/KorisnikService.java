package soa.soa.service;

import soa.soa.entity.LoginDTO;
import soa.soa.entity.Registrovanikorisnik;
import soa.soa.entity.RegkorisnikDTO;

import java.util.List;

public interface KorisnikService {

    Registrovanikorisnik createKorisnik(Registrovanikorisnik regkorisnik, String role) throws Exception;

    LoginDTO proveri(String korisnickoime, String lozinka) throws Exception;

    List<Registrovanikorisnik> findAll() throws Exception;

    Registrovanikorisnik update(Registrovanikorisnik regkorisnik) throws Exception;

    RegkorisnikDTO getone(Long id) throws Exception;

    RegkorisnikDTO findbyusername(String username);
}
