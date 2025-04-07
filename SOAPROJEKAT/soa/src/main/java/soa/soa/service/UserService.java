package soa.soa.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import soa.soa.entity.RegkorisnikDTO;
import soa.soa.util.TokenUtils;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    @Autowired
    private TokenUtils tokenUtils;

    private final KorisnikService korisnikService;

    public UserService(KorisnikService korisnikService)
    {
        this.korisnikService=korisnikService;
    }


    public void getusername(UserRequest request, StreamObserver<UserResponse> responseObserver) {


//        Authentication user1 = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(user1.getName());

        String jwt1 = request.getToken().substring(7);
        String username = tokenUtils.getUsernameFromToken(jwt1);
        System.out.println(username + "!");
        RegkorisnikDTO regkorisnik = korisnikService.findbyusername(username);

        System.out.println(regkorisnik.getId());
        System.out.println(regkorisnik.getEmail());
        System.out.println(regkorisnik.getKorisnickoime());




        UserResponse userResponse = UserResponse.newBuilder()
                .setEmail(regkorisnik.getEmail()).setKorisnickoime(regkorisnik.getKorisnickoime()).setId(regkorisnik.getId())
                .build();
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();

//        return new ResponseEntity<>(regkorisnik, HttpStatus.OK);
    }



    public void abcde( UserRequest1 request1, StreamObserver<UserResponse1> responseObserver)  {




        UserResponse1 userResponse1 = UserResponse1.newBuilder()
                .setAbc(request1.getAbcd())
                .build();
        responseObserver.onNext(userResponse1);
        responseObserver.onCompleted();

//        return new ResponseEntity<>(regkorisnik, HttpStatus.OK);
    }
}
