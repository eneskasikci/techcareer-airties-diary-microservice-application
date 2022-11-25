package com.eneskasikci.diaryapp.controller;

import com.eneskasikci.diaryapp.model.DiaryPosts;
import com.eneskasikci.diaryapp.requests.PostCreateRequest;
import com.eneskasikci.diaryapp.requests.PostDeleteRequest;
import com.eneskasikci.diaryapp.requests.PostListRequest;
import com.eneskasikci.diaryapp.requests.PostUpdateRequest;
import com.eneskasikci.diaryapp.responses.PostResponse;
import com.eneskasikci.diaryapp.service.DiaryUsersService;
import org.springframework.web.bind.annotation.*;
import com.eneskasikci.diaryapp.service.DiaryPostsService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/diaryApp/posts")
public class DiaryPostsController {
    private final DiaryPostsService diaryPostsService;

    public DiaryPostsController(DiaryPostsService diaryPostsService, DiaryUsersService diaryUsersService) {
        this.diaryPostsService = diaryPostsService;
    }

    // http://localhost:5555/api/diaryApp/posts/createDiaryPost
    @PostMapping("/createDiaryPost")
    public DiaryPosts createDiaryPost(@RequestBody PostCreateRequest diaryPostRequest){
        return diaryPostsService.createDiaryPosts(diaryPostRequest);
    }

    // To get the all the diary posts from the database
    // http://localhost:5555/api/diaryApp/posts/getAllPostsFromResponse
    // To get the all the diary posts from the database by any user
    // http://localhost:5555/api/diaryApp/posts/getAllPostsFromResponse?userId=X (X is the user id)

    @GetMapping("/getAllPostsFromResponse")
    public List<PostResponse> getAllDiaryPostsResponse(@RequestParam Optional<Long> userId){
        return diaryPostsService.getAllDiaryPostsResponse(userId);
    }
    @GetMapping("/getAllPostsFromUser/{userName}")
    public List<PostResponse> getPostsFromUser(@PathVariable String userName){
        return diaryPostsService.getAllDiaryPostsFromUser(userName);
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

    @PutMapping("/updatePostIfUserIsOwner")
    public DiaryPosts updatePostIfUserIsOwner(@RequestBody PostUpdateRequest updatePost){
        return diaryPostsService.updatePostIfUserIsOwner(updatePost);
    }

    // delete post if the user is the owner of the post
    @DeleteMapping("/deletePostIfUserIsOwner")
    public Optional<?> deletePostIfUserIsOwner(@RequestBody PostDeleteRequest postDeleteRequest){
        return diaryPostsService.deletePostIfUserIsOwner(postDeleteRequest);
    }
}
