package soa.soa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.soa.entity.LoginDTO;
import soa.soa.entity.Registrovanikorisnik;
import soa.soa.entity.RegkorisnikDTO;
import soa.soa.repository.RegistrovanikorisnikRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikServiceImpl implements KorisnikService{

    private final RegistrovanikorisnikRepository registrovanikorisnikRepository;



    @Autowired
    public KorisnikServiceImpl(RegistrovanikorisnikRepository registrovanikorisnikRepository) {
        this.registrovanikorisnikRepository = registrovanikorisnikRepository;

    }




    @Override
    public Registrovanikorisnik createKorisnik(Registrovanikorisnik registrovanikorisnik) throws Exception {

        if(registrovanikorisnikRepository.existsRegistrovanikorisnikByKorisnickoimeOrLozinkaOrEmail(registrovanikorisnik.getKorisnickoime(), registrovanikorisnik.getLozinka(),registrovanikorisnik.getEmail())) {
            throw new Exception("Korisnik sa takvim korisnickim imenom, lozinkom ili email-om vec postoji!");
        }

        Registrovanikorisnik noviregistrovanikorisnik = this.registrovanikorisnikRepository.save(registrovanikorisnik);
        return noviregistrovanikorisnik;
    }



    @Override
    public LoginDTO proveri(String korisnickoime, String lozinka) throws Exception{
        LoginDTO loginDTO2 = new LoginDTO();
        Registrovanikorisnik regkorisnik = registrovanikorisnikRepository.findByKorisnickoimeAndLozinka(korisnickoime, lozinka);
        if(regkorisnik!= null){
            loginDTO2.setId(regkorisnik.getId());
            loginDTO2.setAktivan(regkorisnik.isAktivan());
            loginDTO2.setUloga(regkorisnik.getUloga());
            loginDTO2.setKorisnickoime(regkorisnik.getKorisnickoime());
            loginDTO2.setLozinka(regkorisnik.getLozinka());
        }

        if(loginDTO2.getKorisnickoime()==null & loginDTO2.getLozinka()==null){
            throw new Exception("Niste uneli tacno korisnicko ime ili lozinku!");
        }
        return loginDTO2;

    }



    @Override
    public List<Registrovanikorisnik> findAll(String uloga) throws Exception {
        if(!uloga.equals("ADMINISTRATOR")){
            throw new Exception("Nije Vam dozvoljen pristup podacima!");
        }

        List<Registrovanikorisnik> sviaktivnikorisnici = this.registrovanikorisnikRepository.findByAktivan(true);
        return sviaktivnikorisnici;
    }




    @Override
    public Registrovanikorisnik update(Registrovanikorisnik regkorisnik, String uloga) throws Exception {
        if(!uloga.equals("ADMINISTRATOR")){
            throw new Exception("Nije vam dozvoljen pristup podacima!");
        }
        Registrovanikorisnik noviregkorisnik = this.registrovanikorisnikRepository.findbyid(regkorisnik.getId());

        noviregkorisnik.setAktivan(false);


        Registrovanikorisnik regkorisnik1 = this.registrovanikorisnikRepository.save(noviregkorisnik);
        return regkorisnik1;


    }


    @Override
    public RegkorisnikDTO getone(Long id) throws Exception {

        Registrovanikorisnik noviregkorisnik = this.registrovanikorisnikRepository.findbyid(id);
        RegkorisnikDTO regkorisnik = new RegkorisnikDTO();
        regkorisnik.setKorisnickoime(noviregkorisnik.getKorisnickoime());
        regkorisnik.setEmail(noviregkorisnik.getEmail());


        return regkorisnik;


    }
}
