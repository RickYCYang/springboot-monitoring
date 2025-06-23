package net.javaguides.springboot.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import net.javaguides.springboot.entity.Post;
import net.javaguides.springboot.service.PostService;

@Service
public class PostServiceImpl implements PostService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Post> fetchAllPosts() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        Post[] posts = restTemplate.getForObject(url, Post[].class);
        return Arrays.asList(posts);
    }

}
