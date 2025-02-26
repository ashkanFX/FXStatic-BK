package com.example.FXstatic.service.impl;

import com.example.FXstatic.dto.PostReqDto;
import com.example.FXstatic.dto.PostResDto;
import com.example.FXstatic.models.Post;
import com.example.FXstatic.models.User;
import com.example.FXstatic.repository.PostRepo;
import com.example.FXstatic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl {
    @Autowired
    public PostRepo postRepo;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public CategoryPostImpl categoryPost;


    public Post creatPost(UserDetails userDetails, PostReqDto postReqDto) {
        Post post = new Post();
        post.setTitle(postReqDto.getTitle());
        post.setDescription(postReqDto.getDescription());
        post.setContext(postReqDto.getContext());

        User user = userRepo.findByUserName(userDetails.getUsername()).orElseThrow();
        post.setUser(user);
        post = postRepo.save(post);
        Post finalPost = post;

        postReqDto.getCategories().forEach(item -> {
            categoryPost.add(finalPost.getId(), item);
        });

        return post;
    }


    public Post updatePost(Long id, PostReqDto postReqDto) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        post.setTitle(postReqDto.getTitle());
        post.setDescription(postReqDto.getDescription());
        post.setContext(postReqDto.getContext());
        postReqDto.getCategories().forEach(item -> {
            categoryPost.add(post.getId(), item);
        });
        return postRepo.save(post);
    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    public List<PostResDto> getAllPost() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(item -> {
            PostResDto postResDto = new PostResDto();
            postResDto.setTitle(item.getTitle());
            postResDto.setId(item.getId());
            postResDto.setCountOfView(item.getCountOfView());
            postResDto.setUpdateAt(item.getUpdateAt());
            postResDto.setCreateAt(item.getCreateAt());
            postResDto.setContext(item.getContext());
            postResDto.setUser(item.getUser());
            postResDto.setDescription(item.getDescription());
            postResDto.setDocument(item.getDocument());
            postResDto.setCategories(categoryPost.getAllPostCategories(item.getId()));
            return postResDto;
        }).collect(Collectors.toList());
    }

    public List<PostResDto> getLatestPost() {
        List<Post> posts = postRepo.getLatest();
        return posts.stream().map(item -> {
            PostResDto postResDto = new PostResDto();
            postResDto.setTitle(item.getTitle());
            postResDto.setId(item.getId());
            postResDto.setContext(item.getContext());
            postResDto.setUser(item.getUser());
            postResDto.setCountOfView(item.getCountOfView());
            postResDto.setDescription(item.getDescription());
            postResDto.setDocument(item.getDocument());
            postResDto.setCategories(categoryPost.getAllPostCategories(item.getId()));
            return postResDto;
        }).collect(Collectors.toList());

    }

    public PostResDto findById(Long id) {
        PostResDto postResDto = new PostResDto();
        Post post = postRepo.findById(id).orElseThrow();
        postResDto.setTitle(post.getTitle());
        postResDto.setId(post.getId());
        postResDto.setContext(post.getContext());
        postResDto.setCountOfView(post.getCountOfView());
        postResDto.setUser(post.getUser());
        postResDto.setDescription(post.getDescription());
        postResDto.setDocument(post.getDocument());
        postResDto.setCategories(categoryPost.getAllPostCategories(post.getId()));
        post.setCountOfView(post.getCountOfView() + 1);
        postRepo.save(post);
        return postResDto;
    }

}
