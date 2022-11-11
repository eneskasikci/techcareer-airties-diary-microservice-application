package com.eneskasikci.diaryapp.requests;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
public class PostCreateRequest {
    @Id
    @SequenceGenerator(name = "seq_diaryPosts", allocationSize = 1)
    @GeneratedValue(generator = "seq_diaryPosts", strategy = GenerationType.SEQUENCE)
    private Long request_diaryId;
    private String request_diaryTitle;
    private String request_diaryContent;
    private Long request_diaryUserId;

}
