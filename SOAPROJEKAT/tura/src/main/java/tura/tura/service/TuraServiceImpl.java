package tura.tura.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tura.tura.entity.*;
import tura.tura.repository.KorpaRepository;
import tura.tura.repository.TuraRepository;
import tura.tura.repository.TuraukorpiRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TuraServiceImpl implements TuraService {

    private final TuraRepository turaRepository;

    private final KorpaRepository korpaRepository;

    private final TuraukorpiRepository turaukorpiRepository;

    @Value("soa:9091")
    private String serverAddress;




    @Autowired
    public TuraServiceImpl(TuraRepository turaRepository, KorpaRepository korpaRepository, TuraukorpiRepository turaukorpiRepository) {
        this.turaRepository = turaRepository;
        this.korpaRepository = korpaRepository;
        this.turaukorpiRepository = turaukorpiRepository;

    }



    @Override
    public Tura createTura(Tura tura, String token) throws Exception {


//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        token = token.substring(7);
//        headers.set("Authorization", "Bearer " + token);
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<RegkorisnikDTO> regkorisnikDTO = restTemplate.exchange(
//                "http://localhost:8081/users1/api/korisnik/getusername",
//                HttpMethod.GET,
//                requestEntity,
//                RegkorisnikDTO.class
//        );
//
//        tura.setIdkreatorature(regkorisnikDTO.getBody().getId());
//        Tura tura1 = this.turaRepository.save(tura);
//        return tura1;





        System.out.println(token);
        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        UserRequest request = UserRequest.newBuilder().setToken(token).build();



        UserResponse response = stub.getusername(request);
        System.out.println(response.getEmail());

        RegkorisnikDTO regkorisnikDTO = new RegkorisnikDTO();
        regkorisnikDTO.setEmail(response.getEmail());
        regkorisnikDTO.setKorisnickoime(response.getKorisnickoime());
        regkorisnikDTO.setId(response.getId());
        tura.setIdkreatorature(regkorisnikDTO.getId());
        Tura tura1 = this.turaRepository.save(tura);
        return tura1;

    }



    @Override
    public List<Tura> sveture() throws Exception{



        List<Tura> sveture = this.turaRepository.findAll();


        return sveture;
    }



    @Override
    public List<Tura> svekreiraneture(String token) throws Exception{



//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        token = token.substring(7);
//        headers.set("Authorization", "Bearer " + token);
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<RegkorisnikDTO> regkorisnikDTO = restTemplate.exchange(
//                "http://localhost:8081/users1/api/korisnik/getusername",
//                HttpMethod.GET,
//                requestEntity,
//                RegkorisnikDTO.class
//        );


        System.out.println(token);
        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        UserRequest request = UserRequest.newBuilder().setToken(token).build();



        UserResponse response = stub.getusername(request);
        System.out.println(response.getEmail());

        RegkorisnikDTO regkorisnikDTO = new RegkorisnikDTO();
        regkorisnikDTO.setEmail(response.getEmail());
        regkorisnikDTO.setKorisnickoime(response.getKorisnickoime());
        regkorisnikDTO.setId(response.getId());

        Long idkreatora = response.getId();

        List<Tura> sveture = this.turaRepository.findbyidkreatorature(idkreatora);


        return sveture;
    }



    @Override
    public Korpa novakorpa() throws Exception {

        Korpa korpa = new Korpa();
        Korpa korpa1 = this.korpaRepository.save(korpa);
        return korpa1;
    }



    @Override
    public Set<TuraDTO> svetureukorpi(String idkorpe, String tura1, String brojljudi) throws Exception {
        Long lkorpa = Long.parseLong(idkorpe);
        Long ltura = Long.parseLong(tura1);
        int brojljudi1 = Integer.parseInt(brojljudi);

        Korpa korpa = this.korpaRepository.findbyid(lkorpa);
//        Tura tura2 = this.turaRepository.findbyid(ltura);

        Turaukorpi turaukorpi = new Turaukorpi();

        List<Turaukorpi> sveturekorpe = this.turaukorpiRepository.findbykorpaid(lkorpa);
        turaukorpi.setBrojljudi(brojljudi1);
        turaukorpi.setIdture(ltura);
        Turaukorpi turaukorpi11 = this.turaukorpiRepository.save(turaukorpi);
        sveturekorpe.add(turaukorpi11);
        korpa.setTure(sveturekorpe);
        this.korpaRepository.save(korpa);

        Set<TuraDTO> turedto = new HashSet<TuraDTO>();
        for(Turaukorpi turaukorpi12: sveturekorpe)
        {
            TuraDTO turadto1 = new TuraDTO();
            Tura tura21 = this.turaRepository.findbyid(turaukorpi12.getIdture());
            turadto1.setNaziv(tura21.getNaziv());
            turadto1.setCena(tura21.getCena());
            turadto1.setBrojljudi(turaukorpi12.getBrojljudi());
            turadto1.setId(turaukorpi12.getId());
            turedto.add(turadto1);

        }

        return turedto;
    }




    @Override
    public Set<TuraDTO> ukloniizkorpe(String idkorpe, String turaukorpi) throws Exception {
        Long lkorpa = Long.parseLong(idkorpe);
        Long ltura = Long.parseLong(turaukorpi);

        Korpa korpa = this.korpaRepository.findbyid(lkorpa);
        Turaukorpi tura2 = this.turaukorpiRepository.findbyidukorpi(ltura);
//        System.out.println(tura2.getId());
//        List<Turaukorpi> sveturekorpe = this.turaukorpiRepository.findbykorpaid(lkorpa);
//        List<Turaukorpi> sveturekorpe1 = new ArrayList<Turaukorpi>();
//        System.out.println(tura2.getId());
//        String a = tura2.getId().toString();
//                for(Turaukorpi turaukor1: sveturekorpe)
//                {
//                    String b = turaukor1.getId().toString();
//                    if(!a.equals(b));
//                    {
//                        System.out.println(turaukor1.getId());
//
//                        sveturekorpe1.add(turaukor1);
//                    }
//                }
//
//
//
//        korpa.setTure(sveturekorpe1);
//        this.korpaRepository.save(korpa);




        this.turaukorpiRepository.deleteturuukorpi(lkorpa,ltura);
        List<Turaukorpi> sveturekorpe = this.turaukorpiRepository.findbykorpaid(lkorpa);
        Set<TuraDTO> turedto = new HashSet<TuraDTO>();
        for(Turaukorpi turaukorpi12: sveturekorpe)
        {
            TuraDTO turadto1 = new TuraDTO();
//            System.out.println(turaukorpi12.getId());
            Tura tura21 = this.turaRepository.findbyid(turaukorpi12.getIdture());
            turadto1.setNaziv(tura21.getNaziv());
            turadto1.setCena(tura21.getCena());
            turadto1.setBrojljudi(turaukorpi12.getBrojljudi());
            turadto1.setId(turaukorpi12.getId());
            turedto.add(turadto1);

        }


        return turedto;
    }

}
