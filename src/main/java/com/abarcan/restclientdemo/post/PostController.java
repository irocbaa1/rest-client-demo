package com.abarcan.restclientdemo.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post findOne(@PathVariable Integer id) {
        return postService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Integer id, @RequestBody Post post) {
        return postService.update(id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        postService.delete(id);
    }
}
