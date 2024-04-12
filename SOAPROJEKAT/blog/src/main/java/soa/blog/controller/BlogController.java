package soa.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.blog.entity.Blog;
import soa.blog.entity.Komentar;
import soa.blog.service.BlogService;

import java.util.List;

@RestController
@RequestMapping("/api/blogovi")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }




    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/noviblog")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) throws Exception {

        Blog noviblog = blogService.createBlog(blog);


        return new ResponseEntity<>(noviblog, HttpStatus.CREATED);
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/svi")
    public ResponseEntity<List<Blog>> nadjiblogove() throws Exception{

        List<Blog> sviblogovi  = this.blogService.sviblogovi();



        return new ResponseEntity<>(sviblogovi, HttpStatus.CREATED);

    }




    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/novikomentar")
    public ResponseEntity<Komentar> dodajnovikomentar(@RequestBody Komentar komentar, @RequestParam(value = "blog1")  String blog1) throws Exception {

        Komentar komentar1 = this.blogService.dodajnovikomentar(komentar, blog1);


        return new ResponseEntity<>(komentar1, HttpStatus.CREATED);
    }

}
