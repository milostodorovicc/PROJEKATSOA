package tura.tura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tura.tura.entity.Korpa;
import tura.tura.entity.Tura;
import tura.tura.entity.TuraDTO;
import tura.tura.service.TuraService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tours/api/ture")
@CrossOrigin(origins = {"http://localhost:8081"})
public class TuraController {


    private final TuraService turaService;



    @Autowired
    public TuraController(TuraService turaService) {
        this.turaService = turaService;
    }




    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/novatura")
    public ResponseEntity<Tura> createTura(@RequestBody Tura tura) throws Exception {

        Tura novatura = turaService.createTura(tura);


        return new ResponseEntity<>(novatura, HttpStatus.CREATED);
    }




    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/sve")
    public ResponseEntity<List<Tura>> nadjiture() throws Exception{

        List<Tura> sveture  = this.turaService.sveture();



        return new ResponseEntity<>(sveture, HttpStatus.CREATED);

    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/novakorpa")
    public ResponseEntity<Korpa> novakorpa() throws Exception{

        Korpa korpa1  = this.turaService.novakorpa();



        return new ResponseEntity<>(korpa1, HttpStatus.CREATED);

    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/dodajukorpu")
    public ResponseEntity<Set<TuraDTO>> dodajukorpu(@RequestParam(value = "idkorpe") String idkorpe, @RequestParam(value = "tura1") String tura1, @RequestParam(value = "brojljudi") String brojljudi) throws Exception {

        Set<TuraDTO> svetureukorpi = this.turaService.svetureukorpi(idkorpe, tura1, brojljudi);


        return new ResponseEntity<>(svetureukorpi, HttpStatus.OK);
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/ukloniizkorpe")
    public ResponseEntity<Set<TuraDTO>> ukloniizkorpe(@RequestParam(value = "idkorpe") String idkorpe, @RequestParam(value = "turaukorpi") String turaukorpi) throws Exception {

        Set<TuraDTO> ukloniizkorpe = this.turaService.ukloniizkorpe(idkorpe, turaukorpi);


        return new ResponseEntity<>(ukloniizkorpe, HttpStatus.OK);
    }
}
