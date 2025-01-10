package com.example.FXstatic.service.impl;

import com.example.FXstatic.dto.PostReqDto;
import com.example.FXstatic.models.Post;
import com.example.FXstatic.models.User;
import com.example.FXstatic.repository.PostRepo;
import com.example.FXstatic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl {
    @Autowired
    public PostRepo postRepo;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    private DocumentImpl documentService;


    public Post creatPost(UserDetails userDetails, PostReqDto postReqDto) {
        Post post = new Post();
        post.setTitle(postReqDto.getTitle());
        post.setDescription(postReqDto.getDescription());
        post.setContext(postReqDto.getContext());
        User user = userRepo.findByUserName(userDetails.getUsername()).orElseThrow();
        post.setUser(user);
        return postRepo.save(post);
    }


    public Post updatePost(Long id, PostReqDto postReqDto) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        post.setTitle(postReqDto.getTitle());
        post.setDescription(postReqDto.getDescription());
        post.setContext(postReqDto.getContext());
        return postRepo.save(post);
    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    public List<Post> getAllPost() {
        return postRepo.findAll();
    }

    public List<Post> getLatestPost() {
        return postRepo.getLatest();
    }

    public Post findAllById(Long id) {
            System.out.println("test");
//        System.out.println(documentService.findByPostId(id));
        return postRepo.findById(id).orElseThrow();

    }

//    public List<Post> getPostOfUser(String owner) {
//        return postRepo.findPostsByOwnerUser(owner);
//    }


}
