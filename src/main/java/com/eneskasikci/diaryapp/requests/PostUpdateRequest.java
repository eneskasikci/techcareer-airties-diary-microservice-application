package com.eneskasikci.diaryapp.requests;

import lombok.Data;

import javax.persistence.Id;

@Data
public class PostUpdateRequest {
    @Id
    private Long request_diaryUpdatedPostId;
    private String request_diaryUpdatedPostTitle;
    private String request_diaryUpdatedPostContent;
    private String request_diaryUpdatedPost_userName;

}