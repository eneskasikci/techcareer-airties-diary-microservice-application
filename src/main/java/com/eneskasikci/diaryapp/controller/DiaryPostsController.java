package com.eneskasikci.diaryapp.controller;

import com.eneskasikci.diaryapp.model.DiaryPosts;
import com.eneskasikci.diaryapp.requests.PostCreateRequest;
import com.eneskasikci.diaryapp.requests.PostUpdateRequest;
import org.springframework.web.bind.annotation.*;
import com.eneskasikci.diaryapp.service.DiaryPostsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diaryApp/posts")
public class DiaryPostsController {
    private final DiaryPostsService diaryPostsService;

    public DiaryPostsController(DiaryPostsService diaryPostsService) {
        this.diaryPostsService = diaryPostsService;
    }

    // http://localhost:5555/api/diaryApp/posts/saveDiaryPost
    @PostMapping("/saveDiaryPost")
    public DiaryPosts createDiaryPost(@RequestBody PostCreateRequest diaryPostRequest){
        return diaryPostsService.createDiaryPosts(diaryPostRequest);
    }

    // To get the all the diary posts from the database
    // http://localhost:5555/api/diaryApp/posts/getAllPosts
    // To get the all the diary posts from the database by any user
    // http://localhost:5555/api/diaryApp/posts/getAllPosts?userId=X (X is the user id)
    @GetMapping("/getAllPosts")
    public List<DiaryPosts> getAllPosts(@RequestParam Optional<Long> userId){
        return diaryPostsService.getAllDiaryPosts(userId);
    }

    // After given its ID, it shows the Post
    // http://localhost:5555/api/diaryApp/posts/1 -> this brings the first post in the DB if GetMapping
    @GetMapping("/{postId}")
    public DiaryPosts getOnePost(@PathVariable Long postId){
        return diaryPostsService.getPostFromPostId(postId);
    }

    // After given its ID, updates the Post
    // http://localhost:5555/api/diaryApp/posts/updatePost/1 -> this updates the first post in the DB, select PutMapping
    @PutMapping("/updatePost/{postId}")
    public DiaryPosts updatePostById(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return diaryPostsService.updatePostById(postId, updatePost);
    }

    // After given its ID, deletes the Post
    // http://localhost:5555/api/diaryApp/posts/deletePost/1 -> this deletes the first post in the DB, select DeleteMapping
    @DeleteMapping("/deletePost/{postId}")
    public void deletePostById(@PathVariable Long postId){
        diaryPostsService.deletePostById(postId);
    }
}
