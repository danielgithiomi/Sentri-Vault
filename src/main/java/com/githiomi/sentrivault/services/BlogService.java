package com.githiomi.sentrivault.services;

import com.githiomi.sentrivault.data.domain.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogs();

    Blog createNewBlog(Blog blog);
}
