package com.example.FXstatic.controllers;

import com.example.FXstatic.dto.Post.PostReqDto;
import com.example.FXstatic.dto.Post.PostResDto;
import com.example.FXstatic.models.Post;
import com.example.FXstatic.service.impl.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping()
    public ResponseEntity<?> createPost(@Valid @RequestBody PostReqDto postReqDto,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        Post post = postService.creatPost(userDetails, postReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }


    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostReqDto postReqDto) {
        return postService.updatePost(id, postReqDto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("get/all")
    public List<PostResDto> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("public/get/latest")
    public List<PostResDto> getLatestPost() {
        return postService.getLatestPost();
    }

    @GetMapping("public/get/{id}")
    public PostResDto getById(@PathVariable Long id) {
        return postService.findById(id);
    }

}
