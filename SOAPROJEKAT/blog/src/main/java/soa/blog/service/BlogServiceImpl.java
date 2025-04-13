package soa.blog.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import soa.blog.entity.*;
import soa.blog.repository.BlogRepository;
import soa.blog.repository.KomentarRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.TRUE;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final KomentarRepository komentarRepository;
    private static final String UPLOAD_DIR = "images";



    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, KomentarRepository komentarRepository) {
        this.blogRepository = blogRepository;
        this.komentarRepository = komentarRepository;

    }



    @Override
    public Blog createBlog(Blog noviblog) throws Exception {

        noviblog.setDatum(LocalDate.now());
        noviblog.setStatus(Status.PUBLISHED);
        Blog blog1 = this.blogRepository.save(noviblog);


        return blog1;

    }




    @Override
    public String saveblogimages(List<MultipartFile> files, Long idbloga) throws Exception {


        String uploadDir = System.getenv("UPLOAD_PATH");
        if (uploadDir == null || uploadDir.isEmpty()) {
            uploadDir = "uploaded_images";
        }


        File directory = new File(uploadDir);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new IOException("Failed to create upload directory: " + uploadDir);
            }
        }


        int i = 0;
        for (MultipartFile image : files) {
            try {
                byte[] bytes = image.getBytes();


                Path path = Paths.get(directory.getAbsolutePath(), idbloga + "_" + i + ".png");


                Files.write(path, bytes);

                System.out.println("Saved image to: " + path.toString());
                i++;
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to upload " + image.getOriginalFilename() + ": " + e.getMessage();
            }
        }

        return "Files uploaded successfully!";
    }


    @Override
    public List<Blog> sviblogovi() throws Exception{



        List<Blog> sviblogovi = this.blogRepository.findAll();


        return sviblogovi;
}


    @Override
    public Komentar dodajnovikomentar(Komentar komentar, String blog1, String token) throws Exception{

        if(blog1.equals("undefined") ){
            throw new Exception("Niste izabrali blog!");
        }
        if(blog1.equals("null")){
            throw new Exception("Niste izabrali blog!");
        }

        RestTemplate restTemplate = new RestTemplate();
//        String otherServiceUrl = "http://localhost:8081/users1/api/korisnik/getusername";
//        RegkorisnikDTO regkorisnikDTO = restTemplate.getForObject(otherServiceUrl, RegkorisnikDTO.class);

        HttpHeaders headers = new HttpHeaders();
        token = token.substring(7);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<RegkorisnikDTO> regkorisnikDTO = restTemplate.exchange(
                "http://gateway:8081/users1/api/korisnik/getusername",
                HttpMethod.GET,
                requestEntity,
                RegkorisnikDTO.class
        );







        Long l = Long.parseLong(blog1);

        Blog blog2 = this.blogRepository.findbyid(l);
        komentar.setDatumkreiranja(LocalDate.now());
        komentar.setIdkorisnika(regkorisnikDTO.getBody().getId());
        komentar.setDatumposlednjeizmene(LocalDate.now());
        Komentar komentar2 = this.komentarRepository.save(komentar);
        Set<Komentar> svikomentaribloga = this.komentarRepository.findbyblogid(blog2.getId());
        svikomentaribloga.add(komentar2);
        blog2.setKomentari(svikomentaribloga);
        this.blogRepository.save(blog2);


        return komentar2;




    }




    @Override
    public Blog jedanblog(String blogid) throws Exception{



        Long l = Long.parseLong(blogid);
        Blog blog2 = this.blogRepository.findbyid(l);




        return blog2;
    }



    @Override
    public List<String> slikebloga(String idblog) throws Exception {


        String uploadDir = System.getenv("UPLOAD_PATH");
        if (uploadDir == null || uploadDir.isEmpty()) {
            uploadDir = "uploaded_images";
        }


        List<String> imageUrls = new ArrayList<>();
        String prefix = idblog + "_";

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(uploadDir))) {
            for (Path path : directoryStream) {
                if (Files.isRegularFile(path) && path.getFileName().toString().startsWith(prefix)) {
                    System.out.println("Found: " + path);
                    imageUrls.add(path.getFileName().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Could not read image directory");
        }

        if (imageUrls.isEmpty()) {
            throw new Exception("No images found for blog ID: " + idblog);
        }

        return imageUrls;
    }




    @Override
    public Set<KomentarDTO> svikomentari(String blog1) throws Exception{
        Set<KomentarDTO> svikomentari1 = new HashSet<KomentarDTO>();

        Long l = Long.parseLong(blog1);
        Set<Komentar> svikomentari = this.komentarRepository.findbyblogid(l);

        for(Komentar komentar: svikomentari)
        {
            KomentarDTO komentar1 = new KomentarDTO();
            komentar1.setDatum(komentar.getDatumkreiranja());
            komentar1.setTekst(komentar.getTekst());


            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            token = token.substring(7);
//            headers.set("Authorization", "Bearer " + token);
//
//            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//            ResponseEntity<RegkorisnikDTO> regkorisnikDTO = restTemplate.exchange(
//                    "http://localhost:8081/users1/api/korisnik/getone/" + komentar.getIdkorisnika(),
//                    HttpMethod.GET,
//                    requestEntity,
//                    RegkorisnikDTO.class
//            );










            String otherServiceUrl = "http://gateway:8081/users1/api/korisnik/getone/" + komentar.getIdkorisnika();
            RegkorisnikDTO regkorisnik = restTemplate.getForObject(otherServiceUrl, RegkorisnikDTO.class);
            komentar1.setKorisnickoime(regkorisnik.getKorisnickoime());
            svikomentari1.add(komentar1);

        }


        return svikomentari1;
    }
}