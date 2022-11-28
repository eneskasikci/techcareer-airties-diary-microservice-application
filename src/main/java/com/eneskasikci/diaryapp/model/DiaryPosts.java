package com.eneskasikci.diaryapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(description = "These are the requirement variables for the diary posts.")
@Table(name = "diaryPosts")
public class DiaryPosts implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_diaryPosts", allocationSize = 1)
    @GeneratedValue(generator = "seq_diaryPosts", strategy = GenerationType.SEQUENCE)
    @Column(name = "diaryPosts_id")
    @ApiModelProperty(value = "Diary Posts ID. Automatically generated by the database.")
    private long diaryId;

    @NotEmpty(message = "Diary title cannot be empty")
    @ApiModelProperty(value = "Diary Posts Title. Cannot be empty.")
    @Column(name = "diaryPosts_title", nullable = false, columnDefinition = "TEXT")
    private String diaryTitle;

    @NotEmpty(message = "Diary content cannot be empty")
    @ApiModelProperty(value = "Diary Posts Content. Cannot be empty.")
    @Column(name = "diaryPosts_content", nullable = false, columnDefinition = "TEXT")
    private String diaryContent;

    @Column(name = "diaryPosts_CreatedDate", nullable = false)
    @ApiModelProperty(value = "Diary Posts Created Date. Automatically generated by the database.")
    private LocalDate diaryCreatedDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "Many to one relationship with the Diary Users. One user can have many posts.")
    @JoinColumn(name = "diaryUser_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    DiaryUsers diaryUsers;



}
