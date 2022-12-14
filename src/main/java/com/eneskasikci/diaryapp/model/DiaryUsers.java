package com.eneskasikci.diaryapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(description = "These are the requirement variables for the diary users." +
        "We are not storing password in the database. They are being stored in the Register/Login application." +
        "Secured with Spring Security." +
        "Username and the ID is enough to identify the user and it's posts.")
@Table(name = "diaryUsers")
public class DiaryUsers implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_diaryUsers", allocationSize = 1)
    @GeneratedValue(generator = "seq_diaryUsers", strategy = GenerationType.SEQUENCE)
    @ApiParam(value = "Diary Users ID. Automatically generated by the database.")
    @Column(name = "diaryUser_id")
    private long userId;

    @Column(name = "diaryUser_name", nullable = false, columnDefinition = "TEXT")
    @ApiParam(value = "Diary Users Name. Cannot be empty.")
    private String userName;
}
