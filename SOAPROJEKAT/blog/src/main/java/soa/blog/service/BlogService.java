package soa.blog.service;

import soa.blog.entity.Blog;
import soa.blog.entity.Komentar;

import java.util.List;

public interface BlogService {

    Blog createBlog(Blog blog) throws Exception;

    List<Blog> sviblogovi() throws Exception;

    Komentar dodajnovikomentar(Komentar komentar, String blog1) throws Exception;
}
