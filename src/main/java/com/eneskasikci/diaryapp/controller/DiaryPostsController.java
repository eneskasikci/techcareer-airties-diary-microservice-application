package com.eneskasikci.diaryapp.controller;

import com.eneskasikci.diaryapp.model.DiaryPosts;
import com.eneskasikci.diaryapp.requests.PostCreateRequest;
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
        return diaryPostsService.saveDiaryPosts(diaryPostRequest);
    }

    // http://localhost:5555/api/diaryApp/posts/getAllPosts
    @GetMapping("/getAllPosts")
    public List<DiaryPosts> getAllPosts(@RequestParam Optional<Long> userId){
        return diaryPostsService.getAllDiaryPosts(userId);
    }
}
