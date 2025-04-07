package soa.soa.controller;

import io.grpc.stub.StreamObserver;
import jakarta.servlet.http.HttpServletResponse;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import soa.soa.entity.*;
import soa.soa.security.TokenBasedAuthentication;
import soa.soa.service.KorisnikService;
import soa.soa.service.UserRequest;
import soa.soa.service.UserResponse;
import soa.soa.service.UserServiceGrpc;
import soa.soa.util.TokenUtils;


import java.security.Principal;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/users1/api/korisnik")
@CrossOrigin(origins = {"http://localhost:8081"})
public class KorisnikController extends UserServiceGrpc.UserServiceImplBase {




    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


    private final KorisnikService korisnikService;

    private UserDetailsService userDetailsService;


    @Autowired
    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/registrovanikorisnik")
    public ResponseEntity<Registrovanikorisnik> createKorisnik(@RequestBody Registrovanikorisnik registrovanikorisnik,@RequestParam(value = "role") String role) throws Exception {

        Registrovanikorisnik regkorisnik = korisnikService.createKorisnik(registrovanikorisnik,role);


        return new ResponseEntity<>(regkorisnik, HttpStatus.CREATED);
    }





//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE, value = "/login")
//    public ResponseEntity<LoginDTO> potvrdilogin(@RequestBody Registrovanikorisnik regkorisnik) throws Exception {
//
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                regkorisnik.getEmail(),regkorisnik.getPassword()));
//
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        Registrovanikorisnik user = (Registrovanikorisnik) authentication.getPrincipal();
//        String jwt = tokenUtils.generateToken(user.getUsername());
//        int expiresIn = tokenUtils.getExpiredIn();
//
//        LoginDTO loginDTO1 = new LoginDTO();
//        loginDTO1 = korisnikService.proveri(regkorisnik.getUsername(), regkorisnik.getPassword());
//
//
//        return new ResponseEntity<>(loginDTO1, HttpStatus.CREATED);
//
//
//    }


    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping("/login1")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody Registrovanikorisnik regkorisnik, HttpServletResponse response) {
        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                regkorisnik.getUsername(),regkorisnik.getPassword()));



        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        Registrovanikorisnik user = (Registrovanikorisnik) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        System.out.println(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        Set<Role> uloge = user.getRoles();
        List<Role> uloge1 = List.copyOf(uloge);
        String uloga2 = uloge1.get(0).getName();
        String uloga = uloga2;

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,uloga2));
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/getall")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMINISTRATOR')")
    public ResponseEntity<List<Registrovanikorisnik>> getKorisnici() throws Exception {


        Authentication user1 = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user1.getName());
        List<Registrovanikorisnik> sviaktivnikorisnici = this.korisnikService.findAll();

        return new ResponseEntity<>(sviaktivnikorisnici, HttpStatus.OK);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/validatetoken")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_VODIC','ROLE_TURISTA')")
    public String validation() throws Exception {

       String a = "Validacija tokena je uspesna!";
       System.out.println("Validacija!!!");

        return a;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/validatetoken1")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_VODIC')")
    public String validation1() throws Exception {

        String a = "Validacija tokena je uspesna!";
        System.out.println("Validacija!!!");

        return a;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/validatetoken2")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_TURISTA')")
    public String validation2() throws Exception {

        String a = "Validacija tokena je uspesna!";
        System.out.println("Validacija!!!");

        return a;
    }




    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/getusername")
    public ResponseEntity<RegkorisnikDTO> getusername(Authentication authentication, UserRequest request, StreamObserver<UserResponse> responseObserver) throws Exception {


        Authentication user1 = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());



        RegkorisnikDTO regkorisnik = korisnikService.findbyusername(user1.getName());


        //UserResponse userResponse = UserResponse.newBuilder()
        //        .setEmail(regkorisnik.getEmail()).setKorisnickoime(regkorisnik.getEmail()).setId(regkorisnik.getId())
        //        .build();
        //responseObserver.onNext(userResponse);
        //responseObserver.onCompleted();






        return new ResponseEntity<>(regkorisnik, HttpStatus.OK);
    }


//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ADMINISTRATOR')")
//    public ResponseEntity<List<Registrovanikorisnik>> getKorisnici(@RequestParam(value = "uloga") String uloga) throws Exception {
//
//        List<Registrovanikorisnik> sviaktivnikorisnici = this.korisnikService.findAll(uloga);
//
//
//        return new ResponseEntity<>(sviaktivnikorisnici, HttpStatus.OK);
//    }




    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMINISTRATOR')")
    public ResponseEntity<Registrovanikorisnik> getEmployee(@PathVariable("id") Long id) throws Exception {

        Registrovanikorisnik regkorisnik = new Registrovanikorisnik();
        regkorisnik.setId(id);
        Registrovanikorisnik regkorisnik1 = korisnikService.update(regkorisnik);


        return new ResponseEntity<>(regkorisnik1, HttpStatus.OK);
    }


    @GetMapping(value = "/getone/{id1}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegkorisnikDTO> getOne(@PathVariable("id1") Long id1) throws Exception {


        RegkorisnikDTO regkorisnik1 = korisnikService.getone(id1);


        return new ResponseEntity<>(regkorisnik1, HttpStatus.OK);
    }





}
