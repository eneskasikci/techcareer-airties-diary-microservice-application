package com.eneskasikci.diaryapp.service;

import com.eneskasikci.diaryapp.model.DiaryPosts;
import com.eneskasikci.diaryapp.model.DiaryUsers;
import com.eneskasikci.diaryapp.requests.PostCreateRequest;
import com.eneskasikci.diaryapp.requests.PostDeleteRequest;
import com.eneskasikci.diaryapp.requests.PostUpdateRequest;
import com.eneskasikci.diaryapp.responses.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.eneskasikci.diaryapp.repository.IDiaryPostsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            diaryUsers = diaryUsersService.saveDiaryUserFromRequest(diaryPostRequest.getRequest_diaryUserId(), diaryPostRequest.getRequest_userName());
        }
        DiaryPosts toSave = new DiaryPosts();
        toSave.setDiaryTitle(diaryPostRequest.getRequest_diaryTitle());
        toSave.setDiaryContent(diaryPostRequest.getRequest_diaryContent());
        toSave.setDiaryUsers(diaryUsers);
        return IDiaryPostsRepository.save(toSave);
    }

    public List<PostResponse> getAllDiaryPosts(Optional<Long> userId){
        List<DiaryPosts> list;
        if(userId.isPresent()) {
            list = IDiaryPostsRepository.findAllByDiaryUsers_UserId(userId.get());
        } else {
        list = IDiaryPostsRepository.findAll();
        }
        return list.stream().map(PostResponse::new).collect(Collectors.toList());
    }

    public DiaryPosts getPostFromPostId(Long postId) {
        return IDiaryPostsRepository.findById(postId).orElse(null);
    }

    public DiaryPosts updatePostById(Long postId, PostUpdateRequest updatePost) {

        Optional<DiaryPosts> post = IDiaryPostsRepository.findById(postId);
        if (post.isPresent()){
            DiaryPosts toUpdate = post.get();
            toUpdate.setDiaryTitle(updatePost.getRequest_diaryUpdatedPostTitle());
            toUpdate.setDiaryContent(updatePost.getRequest_diaryUpdatedPostContent());
            IDiaryPostsRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    // BUG: Returns Retrofit null and this creates a 500 error code in the gateway even though deletion is successfully made.
    // TODO: Fix this bug.
    public ResponseEntity<?> deletePostIfUserIsOwner(PostDeleteRequest postDeleteRequest) {
        // check if there is a post with the requested id
        Optional<DiaryPosts> post = IDiaryPostsRepository.findById(postDeleteRequest.getDeletionrequest_diaryId());
        if (post.isPresent()){
            // check if the user is the owner of the post
            if (post.get().getDiaryUsers().getUserName().equals(postDeleteRequest.getDeletionrequest_userName())){
                IDiaryPostsRepository.deleteById(postDeleteRequest.getDeletionrequest_diaryId());
                return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("You are not the owner of the post", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("There is no post with the requested id", HttpStatus.BAD_REQUEST);
    }

    // BUG: Returns Retrofit null and this creates a 500 error code in the gateway even though update is successfully made.
    // TODO: Fix this bug.
    public ResponseEntity<?> updatePostIfUserIsOwner(PostUpdateRequest updatePost) {
        // check if there is a post with the requested id
        Optional<DiaryPosts> post = IDiaryPostsRepository.findById(updatePost.getRequest_diaryUpdatedPostId());
        if (post.isPresent()){
            // check if the user is the owner of the post
            if (post.get().getDiaryUsers().getUserName().equals(updatePost.getRequest_diaryUpdatedPost_userName())){
                updatePostById(updatePost.getRequest_diaryUpdatedPostId(), updatePost);
                return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("You are not the owner of the post", HttpStatus.BAD_REQUEST);
    }

    public List<PostResponse> getAllDiaryPostsFromUser(String userName) {
        List<DiaryPosts> list;
        if (userName != null) {
            list = IDiaryPostsRepository.findAllByDiaryUsers_UserName(userName);
        } else {
            return null;
        }
        return list.stream().map(PostResponse::new).collect(Collectors.toList());
    }
}
