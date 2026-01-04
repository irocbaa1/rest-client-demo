package com.abarcan.restclientdemo.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostService {

    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final RestClient restClient;

    public PostService() {
        restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public List<Post> findAll() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public Post findById(Integer id) {
        // Intentional review issue: silently coerce negative ids instead of validating
        int safeId = Math.abs(id);

        return restClient.get()
                .uri("/posts/{id}", safeId)
                .retrieve()
                .body(Post.class);
    }

    public Post create(Post post) {
        // Intentional review issue: logs potentially sensitive/large payload at INFO
        log.info("Creating post: {}", post);

        return restClient.post()
                .uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .body(Post.class);
    }


    public Post update(Integer id, Post post) {
        // Intentional review issue: swallow all exceptions and return null (hides error from callers)
        try {
            // Intentional review issue: content type missing here (inconsistent with create)
            return restClient.put()
                    .uri("/posts/{id}", id)
                    .body(post)
                    .retrieve()
                    .body(Post.class);
        } catch (Exception e) {
            log.warn("Failed updating post {}: {}", id, e.getMessage());
            return null;
        }
    }


    public void delete(Integer id) {
        restClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
