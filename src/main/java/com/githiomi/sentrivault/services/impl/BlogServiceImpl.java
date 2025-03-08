package com.githiomi.sentrivault.services.impl;

import com.githiomi.sentrivault.data.domain.Blog;
import com.githiomi.sentrivault.repositories.BlogRepository;
import com.githiomi.sentrivault.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog createNewBlog(Blog blog) {
        return blogRepository.save(blog);
    }

}
