package com.eneskasikci.diaryapp.service;

import com.eneskasikci.diaryapp.model.DiaryPosts;
import com.eneskasikci.diaryapp.model.DiaryUsers;
import com.eneskasikci.diaryapp.requests.PostCreateRequest;
import com.eneskasikci.diaryapp.requests.PostDeleteRequest;
import com.eneskasikci.diaryapp.requests.PostListRequest;
import com.eneskasikci.diaryapp.requests.PostUpdateRequest;
import com.eneskasikci.diaryapp.responses.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.eneskasikci.diaryapp.repository.IDiaryPostsRepository;

import java.net.http.HttpRequest;
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

    public List<PostResponse> getAllDiaryPostsResponse(Optional<Long> userId){
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

    public Optional<?> deletePostIfUserIsOwner(PostDeleteRequest postDeleteRequest) {
        // check if there is a post with the requested id
        Optional<DiaryPosts> post = IDiaryPostsRepository.findById(postDeleteRequest.getDeletionrequest_diaryId());
        if (post.isPresent()){
            // check if the user is the owner of the post
            if (post.get().getDiaryUsers().getUserName().equals(postDeleteRequest.getDeletionrequest_userName())){
                IDiaryPostsRepository.deleteById(postDeleteRequest.getDeletionrequest_diaryId());
                return Optional.of("Post deleted successfully");
            }
            // return http status 403 if the user is not the owner of the post
            return Optional.of(HttpStatus.FORBIDDEN + "You are not the owner of the post");
        }
        return Optional.of("There is no post with the requested id");
    }

    public DiaryPosts updatePostIfUserIsOwner(PostUpdateRequest updatePost) {
        // check if there is a post with the requested id
        Optional<DiaryPosts> post = IDiaryPostsRepository.findById(updatePost.getRequest_diaryUpdatedPostId());
        if (post.isPresent()){
            // check if the user is the owner of the post
            if (post.get().getDiaryUsers().getUserName().equals(updatePost.getRequest_diaryUpdatedPost_userName())){
                return updatePostById(updatePost.getRequest_diaryUpdatedPostId(), updatePost);
            }
        }
        return null;
    }
}
