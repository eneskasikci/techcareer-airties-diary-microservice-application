package com.eneskasikci.diaryapp.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Post List Request", description = "DTO for the Post List Request. " +
        "These are the accessible parameters for the API Requests.")
public class PostListRequest {
    @ApiModelProperty(value = "Username of the owner. We are using it to get the posts with the" +
            "matching username.")
    private String request_userName;
}
