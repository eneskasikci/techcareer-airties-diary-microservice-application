package com.eneskasikci.diaryapp.requests;

import lombok.Data;

import javax.persistence.Id;

@Data
public class PostCreateRequest {
    @Id
    private Long request_diaryId;
    private String request_diaryTitle;
    private String request_diaryContent;
    private String request_userName;
    private Long request_diaryUserId;
}
