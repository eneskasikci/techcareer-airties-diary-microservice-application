package com.eneskasikci.diaryapp.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
@ApiModel(value = "Post Delete Request", description = "DTO for the Post Delete Request. " +
        "These are the accessible parameters for the API Requests.")
public class PostDeleteRequest {
    @ApiModelProperty(value = "Owner of the post. Used to check if the post in the database" +
            "matches with the provided user for the post.")
    private String deletionrequest_userName;
    @ApiModelProperty(value = "Post ID. Used to check if the post in the database" +
            "matches with the provided post ID for the post.")
    private Long deletionrequest_diaryId;
}
