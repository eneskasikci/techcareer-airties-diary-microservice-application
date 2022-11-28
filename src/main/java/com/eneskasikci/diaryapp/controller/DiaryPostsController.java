package com.eneskasikci.diaryapp.controller;

import com.eneskasikci.diaryapp.model.DiaryPosts;
import com.eneskasikci.diaryapp.requests.PostCreateRequest;
import com.eneskasikci.diaryapp.requests.PostDeleteRequest;
import com.eneskasikci.diaryapp.requests.PostUpdateRequest;
import com.eneskasikci.diaryapp.responses.PostResponse;
import com.eneskasikci.diaryapp.service.DiaryUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eneskasikci.diaryapp.service.DiaryPostsService;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Diary Post Controller API Documentation")
@RequestMapping("/api/diaryApp/posts")
public class DiaryPostsController {
    private final DiaryPostsService diaryPostsService;

    public DiaryPostsController(DiaryPostsService diaryPostsService, DiaryUsersService diaryUsersService) {
        this.diaryPostsService = diaryPostsService;
    }

    // http://localhost:5555/api/diaryApp/posts/createDiaryPost
    @PostMapping("/createDiaryPost")
    @ApiOperation(value = "Creates a new Diary Post in the database with a Post Request parameters." +
            "Passing a JSON body.")
    public DiaryPosts createDiaryPost(@RequestBody @ApiParam(value = "Requirements for the post creation." +
            "We are using a DTO.") PostCreateRequest diaryPostRequest){
        return diaryPostsService.createDiaryPosts(diaryPostRequest);
    }

    // To get the all the diary posts from the database
    // http://localhost:5555/api/diaryApp/posts/getAllPosts
    // To get the all the diary posts from the database by any user
    // http://localhost:5555/api/diaryApp/posts/getAllPosts?userId=X (X is the user id)

    @GetMapping("/getAllPosts")
    @ApiOperation(value = "To see every post in the diary database. It shouldn't be accessible because diaries are personal." +
            "But this is here for the testing purposes. " +
            "If you want to see the posts of a specific user, you can pass the userId parameter.")
    public List<PostResponse> getAllDiaryPosts(@RequestParam @ApiParam(value="User's ID.") Optional<Long> userId){
        return diaryPostsService.getAllDiaryPosts(userId);
    }
    @GetMapping("/getAllPostsFromUser/{userName}")
    @ApiOperation(value = "To see diary posts from the user with the given username." +
            "We are passing the username as a path variable." +
            "If the user doesn't exist, it will return null." +
            "This can be limited to the user itself with controls from front-end.")
    public List<PostResponse> getPostsFromUser(@PathVariable @ApiParam(value = "User's username registered in the database.") String userName){
        return diaryPostsService.getAllDiaryPostsFromUser(userName);
    }

    // After given its ID, it shows the Post
    // http://localhost:5555/api/diaryApp/posts/1 -> this brings the first post in the DB if GetMapping
    @GetMapping("/{postId}")
    @ApiOperation(value = "To see a specific post in the diary database." +
            "We are passing the postId." +
            "If the post doesn't exist, it will return null.")
    public DiaryPosts getOnePost(@PathVariable @ApiParam(value="ID of the Post.")Long postId){
        return diaryPostsService.getPostFromPostId(postId);
    }

    // After given its ID, updates the Post
    // http://localhost:5555/api/diaryApp/posts/updatePost/1 -> this updates the first post in the DB, select PutMapping
    @PutMapping("/updatePost/{postId}")
    @ApiOperation(value = "To update a specific post in the diary database." +
            "We are passing the postId as a path variable, JSON Body for the updated title and content." +
            "If the post doesn't exist, it will return null.")
    public DiaryPosts updatePostById(@PathVariable @ApiParam(value="ID of the Post.") Long postId, @RequestBody @ApiParam(value = "To update a post we need specific details." +
            "We are using a DTO for the update operation.") PostUpdateRequest updatePost){
        return diaryPostsService.updatePostById(postId, updatePost);
    }

    @PutMapping("/updatePostIfUserIsOwner")
    @ApiOperation(value = "To update a specific post in the diary database of the given username." +
            "We are passing the update request with the JSON body. If the username has a post with" +
            "the given postId, it will update the post." +
            "If the given username trying to update a post that is not his, it will return a message accordingly.")
    public ResponseEntity<?> updatePostIfUserIsOwner(@RequestBody @ApiParam(value = "Passing a DTO values for the update request.") PostUpdateRequest updatePost){
        return diaryPostsService.updatePostIfUserIsOwner(updatePost);
    }

    // delete post if the user is the owner of the post
    @DeleteMapping("/deletePostIfUserIsOwner")
    @ApiOperation(value = "To delete a specific post in the diary database of the given username." +
            "We are passing the delete request with the JSON body. If the username has a post with" +
            "the given postId, it will delete the post." +
            "If the given username trying to delete a post that is not his," +
            "or the there are no post with the given postId," +
            "it will return a message accordingly.")
    public ResponseEntity<?> deletePostIfUserIsOwner(@RequestBody @ApiParam(value = "Passing a DTO values for the deletion request.") PostDeleteRequest postDeleteRequest){
        return diaryPostsService.deletePostIfUserIsOwner(postDeleteRequest);
    }

    @DeleteMapping("/deletePosts")
    @ApiOperation(value = "To delete all the posts in the database." +
            "This is here for the testing purposes.")
    public void deleteAllPosts(){
        diaryPostsService.deleteAllPosts();
    }
}
