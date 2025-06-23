package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.entity.Post;

public interface PostService {
    List<Post> fetchAllPosts();
}
