package soa.soa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.soa.entity.LoginDTO;
import soa.soa.entity.Registrovanikorisnik;
import soa.soa.entity.RegkorisnikDTO;
import soa.soa.service.KorisnikService;

import java.util.List;

@RestController
@RequestMapping("/users1/api/korisnik")
@CrossOrigin(origins = {"http://localhost:8081"})
public class KorisnikController {

    private final KorisnikService korisnikService;


    @Autowired
    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/registrovanikorisnik")
    public ResponseEntity<Registrovanikorisnik> createKorisnik(@RequestBody Registrovanikorisnik registrovanikorisnik) throws Exception {

        Registrovanikorisnik regkorisnik = korisnikService.createKorisnik(registrovanikorisnik);


        return new ResponseEntity<>(regkorisnik, HttpStatus.CREATED);
    }





    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/login")
    public ResponseEntity<LoginDTO> potvrdilogin(@RequestBody Registrovanikorisnik regkorisnik) throws Exception {




        LoginDTO loginDTO1 = new LoginDTO();
        loginDTO1 = korisnikService.proveri(regkorisnik.getKorisnickoime(), regkorisnik.getLozinka());


        return new ResponseEntity<>(loginDTO1, HttpStatus.CREATED);


    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Registrovanikorisnik>> getKorisnici(@RequestParam(value = "uloga") String uloga) throws Exception {

        List<Registrovanikorisnik> sviaktivnikorisnici = this.korisnikService.findAll(uloga);


        return new ResponseEntity<>(sviaktivnikorisnici, HttpStatus.OK);
    }




    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Registrovanikorisnik> getEmployee(@PathVariable("id") Long id, @RequestParam(value = "uloga") String uloga) throws Exception {

        Registrovanikorisnik regkorisnik = new Registrovanikorisnik();
        regkorisnik.setId(id);
        Registrovanikorisnik regkorisnik1 = korisnikService.update(regkorisnik, uloga);


        return new ResponseEntity<>(regkorisnik1, HttpStatus.OK);
    }


    @GetMapping(value = "/getone/{id1}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegkorisnikDTO> getOne(@PathVariable("id1") Long id1) throws Exception {


        RegkorisnikDTO regkorisnik1 = korisnikService.getone(id1);


        return new ResponseEntity<>(regkorisnik1, HttpStatus.OK);
    }





}
