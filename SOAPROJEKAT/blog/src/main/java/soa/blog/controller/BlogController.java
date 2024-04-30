package soa.blog.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soa.blog.entity.Blog;
import soa.blog.entity.BlogDTO;
import soa.blog.entity.Komentar;
import soa.blog.entity.KomentarDTO;
import soa.blog.service.BlogService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/blogs/api/blogovi")
@CrossOrigin(origins = {"http://localhost:8081"})
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }




    @PostMapping(  value = "/noviblog")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog noviblog) throws Exception {


        Blog noviblog12 = blogService.createBlog(noviblog);
        System.out.println(noviblog.getNaslov());


        return new ResponseEntity<>(noviblog12, HttpStatus.CREATED);
    }


    @PostMapping(  value = "/noviblogimages")
    public String saveblogimages( @RequestParam("files") List<MultipartFile> files, @RequestParam("idbloga") Long idbloga) throws Exception {


        String res =  blogService.saveblogimages(files, idbloga);



        return res;
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/svi")
    public ResponseEntity<List<Blog>> nadjiblogove() throws Exception{

        List<Blog> sviblogovi  = this.blogService.sviblogovi();



        return new ResponseEntity<>(sviblogovi, HttpStatus.CREATED);

    }




    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/novikomentar")
    public ResponseEntity<Komentar> dodajnovikomentar(@RequestBody Komentar komentar, @RequestParam(value = "blog1")  String blog1, @RequestParam(value="idkomentatora") String idkomentatora) throws Exception {

        Komentar komentar1 = this.blogService.dodajnovikomentar(komentar, blog1, idkomentatora);


        return new ResponseEntity<>(komentar1, HttpStatus.CREATED);
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/jedanblog")
    public ResponseEntity<Blog> jedanblog(@RequestParam String blogid) throws Exception{

        Blog jedanblog  = this.blogService.jedanblog(blogid);



        return new ResponseEntity<>(jedanblog, HttpStatus.CREATED);

    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/slikebloga")
    public ResponseEntity<List<String>> slikebloga(@RequestParam String idblog) throws Exception{

        List<String> slikebloga  = this.blogService.slikebloga(idblog);



        return new ResponseEntity<>(slikebloga, HttpStatus.CREATED);

    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/svikomentari")
    public ResponseEntity<Set<KomentarDTO>> nadjiblogove(@RequestParam(value="blog1") String blog1) throws Exception{

        Set<KomentarDTO> svikomentari  = this.blogService.svikomentari(blog1);



        return new ResponseEntity<>(svikomentari, HttpStatus.CREATED);

    }
}
