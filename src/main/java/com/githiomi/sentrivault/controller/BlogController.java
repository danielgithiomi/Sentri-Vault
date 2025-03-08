package com.githiomi.sentrivault.controller;

import com.githiomi.sentrivault.data.domain.Blog;
import com.githiomi.sentrivault.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("")
    public List<Blog> getAll() {
        return blogService.getAllBlogs();
    }

    @PostMapping("")
    public ResponseEntity<Blog> createNewBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogService.createNewBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
    }

}
