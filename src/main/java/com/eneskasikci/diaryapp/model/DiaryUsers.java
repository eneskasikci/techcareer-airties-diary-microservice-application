package com.eneskasikci.diaryapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diaryUsers")
public class DiaryUsers {

    @Id
    @SequenceGenerator(name = "seq_diaryUsers", allocationSize = 1)
    @GeneratedValue(generator = "seq_diaryUsers", strategy = GenerationType.SEQUENCE)
    @Column(name = "diaryUser_id")
    private long userId;

    @Column(name = "diaryUser_name", nullable = false, columnDefinition = "TEXT")
    private String userName;

    @Column(name = "diaryUser_password", nullable = false)
    private String userPassword;
}
