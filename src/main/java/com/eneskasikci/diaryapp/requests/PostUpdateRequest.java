package com.eneskasikci.diaryapp.requests;

import lombok.Data;

@Data
public class PostUpdateRequest {
    String diaryUpdatedPostTitle;
    String diaryUpdatedPostContent;
}