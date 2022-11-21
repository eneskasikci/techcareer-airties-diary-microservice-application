package com.eneskasikci.diaryapp.service;

import com.eneskasikci.diaryapp.model.DiaryPosts;
import com.eneskasikci.diaryapp.model.DiaryUsers;
import com.eneskasikci.diaryapp.requests.PostCreateRequest;
import com.eneskasikci.diaryapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;
import com.eneskasikci.diaryapp.repository.IDiaryPostsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryPostsService {

    private final IDiaryPostsRepository IDiaryPostsRepository;
    private final DiaryUsersService diaryUsersService;

    public DiaryPostsService(IDiaryPostsRepository IDiaryPostsRepository, DiaryUsersService diaryUsersService) {
        this.IDiaryPostsRepository = IDiaryPostsRepository;
        this.diaryUsersService = diaryUsersService;
    }

    public DiaryPosts createDiaryPosts(PostCreateRequest diaryPostRequest){
        DiaryUsers diaryUsers = diaryUsersService.getDiaryUserById(diaryPostRequest.getRequest_diaryUserId());
        if (diaryUsers == null){
            return null;
        }
        DiaryPosts toSave = new DiaryPosts();
        toSave.setDiaryTitle(diaryPostRequest.getRequest_diaryTitle());
        toSave.setDiaryContent(diaryPostRequest.getRequest_diaryContent());
        toSave.setDiaryUsers(diaryUsers);
        return IDiaryPostsRepository.save(toSave);
    }

    public List<DiaryPosts> getAllDiaryPosts(Optional<Long> userId){
        if(userId.isPresent())
            return IDiaryPostsRepository.findAllByDiaryUsers_UserId(userId.get());
        return IDiaryPostsRepository.findAll();
    }

    public DiaryPosts getPostFromPostId(Long postId) {
        return IDiaryPostsRepository.findById(postId).orElse(null);
    }

    public DiaryPosts updatePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<DiaryPosts> post = IDiaryPostsRepository.findById(postId);
        if (post.isPresent()){
            DiaryPosts toUpdate = post.get();
            toUpdate.setDiaryTitle(updatePost.getDiaryUpdatedPostTitle());
            toUpdate.setDiaryContent(updatePost.getDiaryUpdatedPostContent());
            IDiaryPostsRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deletePostById(Long postId) {
        IDiaryPostsRepository.deleteById(postId);
    }
}
