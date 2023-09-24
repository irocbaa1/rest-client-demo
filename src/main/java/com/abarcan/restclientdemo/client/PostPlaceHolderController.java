package com.abarcan.restclientdemo.client;

import com.abarcan.restclientdemo.post.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/posts")
public class PostPlaceHolderController {

    private final JsonPlaceHolderService placeHolderService;

    public PostPlaceHolderController(JsonPlaceHolderService placeHolderService) {
        this.placeHolderService = placeHolderService;
    }

    @RequestMapping("")
    public List<Post> findAll() {
        return placeHolderService.findAll();
    }

    @GetMapping("/{id}")
    public Post findOne(@PathVariable Integer id) {
        return placeHolderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return placeHolderService.create(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Integer id, @RequestBody Post post) {
        return placeHolderService.update(id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        placeHolderService.delete(id);
    }
}
