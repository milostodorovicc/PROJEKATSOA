package soa.soa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soa.soa.entity.LoginDTO;
import soa.soa.entity.Registrovanikorisnik;
import soa.soa.entity.RegkorisnikDTO;
import soa.soa.entity.Role;
import soa.soa.repository.RegistrovanikorisnikRepository;
import soa.soa.repository.RoleRepository;

import java.util.*;

@Service
public class KorisnikServiceImpl implements KorisnikService{

    private final RegistrovanikorisnikRepository registrovanikorisnikRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public KorisnikServiceImpl(RegistrovanikorisnikRepository registrovanikorisnikRepository, RoleRepository roleRepository) {
        this.registrovanikorisnikRepository = registrovanikorisnikRepository;
        this.roleRepository = roleRepository;

    }




    @Override
    public Registrovanikorisnik createKorisnik(Registrovanikorisnik registrovanikorisnik, String role12) throws Exception {

        if(registrovanikorisnikRepository.existsRegistrovanikorisnikByUsernameOrPasswordOrEmail(registrovanikorisnik.getUsername(), registrovanikorisnik.getPassword(),registrovanikorisnik.getEmail())) {
            throw new Exception("Korisnik sa takvim korisnickim imenom, lozinkom ili email-om vec postoji!");
        }
        registrovanikorisnik.setEnabled(true);
        registrovanikorisnik.setPassword(passwordEncoder.encode(registrovanikorisnik.getPassword()));
        Role role = new Role();
        role.setName(role12);
        Role role1 = this.roleRepository.save(role);
        Set<Role> roles = new HashSet<Role>();
        roles.add(role1);
        registrovanikorisnik.setRoles(roles);
        Registrovanikorisnik noviregistrovanikorisnik = this.registrovanikorisnikRepository.save(registrovanikorisnik);
        return noviregistrovanikorisnik;
    }



    @Override
    public LoginDTO proveri(String korisnickoime, String lozinka) throws Exception{
        LoginDTO loginDTO2 = new LoginDTO();
        Registrovanikorisnik regkorisnik = registrovanikorisnikRepository.findByUsernameAndPassword(korisnickoime, lozinka);
        if(regkorisnik!= null){
            loginDTO2.setId(regkorisnik.getId());
            loginDTO2.setAktivan(regkorisnik.isEnabled());
//            loginDTO2.setUloga(regkorisnik.getUloga());
            loginDTO2.setKorisnickoime(regkorisnik.getUsername());
            loginDTO2.setLozinka(regkorisnik.getPassword());
        }

        if(loginDTO2.getKorisnickoime()==null & loginDTO2.getLozinka()==null){
            throw new Exception("Niste uneli tacno korisnicko ime ili lozinku!");
        }
        return loginDTO2;

    }



    @Override
    public List<Registrovanikorisnik> findAll() throws Exception {

        List<Registrovanikorisnik> sviaktivnikorisnici = this.registrovanikorisnikRepository.findByEnabled(true);
        return sviaktivnikorisnici;
    }




    @Override
    public Registrovanikorisnik update(Registrovanikorisnik regkorisnik) throws Exception {

        Registrovanikorisnik noviregkorisnik = this.registrovanikorisnikRepository.findbyid(regkorisnik.getId());

        noviregkorisnik.setEnabled(false);


        Registrovanikorisnik regkorisnik1 = this.registrovanikorisnikRepository.save(noviregkorisnik);
        return regkorisnik1;


    }


    @Override
    public RegkorisnikDTO getone(Long id) throws Exception {

        Registrovanikorisnik noviregkorisnik = this.registrovanikorisnikRepository.findbyid(id);
        RegkorisnikDTO regkorisnik = new RegkorisnikDTO();
        regkorisnik.setId(noviregkorisnik.getId());
        regkorisnik.setKorisnickoime(noviregkorisnik.getUsername());
        regkorisnik.setEmail(noviregkorisnik.getEmail());


        return regkorisnik;


    }


    @Override
    public RegkorisnikDTO findbyusername(String username) {

        Registrovanikorisnik noviregkorisnik = this.registrovanikorisnikRepository.findByUsername(username);
        RegkorisnikDTO regkorisnik = new RegkorisnikDTO();
        regkorisnik.setId(noviregkorisnik.getId());
        regkorisnik.setKorisnickoime(noviregkorisnik.getUsername());
        regkorisnik.setEmail(noviregkorisnik.getEmail());
        return regkorisnik;
    }
}
