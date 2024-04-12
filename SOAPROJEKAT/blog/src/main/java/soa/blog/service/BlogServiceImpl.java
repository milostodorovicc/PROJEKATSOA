package soa.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.blog.entity.Blog;
import soa.blog.entity.Komentar;
import soa.blog.repository.BlogRepository;
import soa.blog.repository.KomentarRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Boolean.TRUE;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final KomentarRepository komentarRepository;



    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, KomentarRepository komentarRepository) {
        this.blogRepository = blogRepository;
        this.komentarRepository = komentarRepository;

    }



    @Override
    public Blog createBlog(Blog blog) throws Exception {

        blog.setDatum(LocalDateTime.now());

        Blog blog1 = this.blogRepository.save(blog);
        return blog1;
    }




    @Override
    public List<Blog> sviblogovi() throws Exception{



        List<Blog> sviblogovi = this.blogRepository.findAll();


        return sviblogovi;
}


    @Override
    public Komentar dodajnovikomentar(Komentar komentar, String blog1) throws Exception{

        if(blog1.equals("undefined") ){
            throw new Exception("Niste izabrali blog!");
        }
        if(blog1.equals("null")){
            throw new Exception("Niste izabrali blog!");
        }
        Long l = Long.parseLong(blog1);
        Blog blog2 = this.blogRepository.findbyid(l);
        komentar.setDatumkreiranja(LocalDateTime.now());
        komentar.setDatumposlednjeizmene(LocalDateTime.now());
        Komentar komentar2 = this.komentarRepository.save(komentar);
        Set<Komentar> svikomentaribloga = this.komentarRepository.findbyblogid(blog2.getId());
        svikomentaribloga.add(komentar2);
        blog2.setKomentari(svikomentaribloga);
        this.blogRepository.save(blog2);


        return komentar2;




    }





}