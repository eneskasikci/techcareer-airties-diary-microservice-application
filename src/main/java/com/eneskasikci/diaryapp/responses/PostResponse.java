package com.eneskasikci.diaryapp.responses;

import com.eneskasikci.diaryapp.model.DiaryPosts;
import lombok.Data;

@Data
public class PostResponse {
    private Long response_diaryId;
    private Long response_diaryUserId;
    private String response_userName;
    private String response_diaryTitle;
    private String response_diaryContent;

    public PostResponse(DiaryPosts entity){
        this.response_diaryId = entity.getDiaryId();
        this.response_diaryUserId = entity.getDiaryUsers().getUserId();
        this.response_userName = entity.getDiaryUsers().getUserName();
        this.response_diaryTitle = entity.getDiaryTitle();
        this.response_diaryContent = entity.getDiaryContent();
    }

}
