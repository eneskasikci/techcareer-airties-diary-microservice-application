package com.eneskasikci.diaryapp.model;

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
@Table(name = "diaryPosts")
public class DiaryPosts implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_diaryPosts", allocationSize = 1)
    @GeneratedValue(generator = "seq_diaryPosts", strategy = GenerationType.SEQUENCE)
    @Column(name = "diaryPosts_id")
    private long diaryId;

    @NotEmpty(message = "Diary title cannot be empty")
    @Column(name = "diaryPosts_title", nullable = false, columnDefinition = "TEXT")
    private String diaryTitle;

    @NotEmpty(message = "Diary content cannot be empty")
    @Column(name = "diaryPosts_content", nullable = false, columnDefinition = "TEXT")
    private String diaryContent;

    @Column(name = "diaryPosts_CreatedDate", nullable = false)
    private LocalDate diaryCreatedDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diaryUser_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    DiaryUsers diaryUsers;



}
