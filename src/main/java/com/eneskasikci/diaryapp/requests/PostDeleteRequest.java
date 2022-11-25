package com.eneskasikci.diaryapp.requests;

import lombok.Data;

import javax.persistence.Id;

@Data
public class PostDeleteRequest {
    private String deletionrequest_userName;
    private Long deletionrequest_diaryId;
}
