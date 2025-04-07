package soa.blog.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import soa.blog.entity.Blog;
import soa.blog.entity.BlogDTO;
import soa.blog.entity.Komentar;
import soa.blog.entity.KomentarDTO;

import java.util.List;
import java.util.Set;

public interface BlogService {

    Blog createBlog(Blog noviblog) throws Exception;

    List<Blog> sviblogovi() throws Exception;

    Komentar dodajnovikomentar(Komentar komentar, String blog1, String token) throws Exception;

    String saveblogimages(List<MultipartFile> files, Long idbloga) throws Exception;

    Blog jedanblog(String blogid) throws Exception;

    List<String> slikebloga(String idblog) throws Exception;

    Set<KomentarDTO> svikomentari(String blog1) throws Exception;
}
