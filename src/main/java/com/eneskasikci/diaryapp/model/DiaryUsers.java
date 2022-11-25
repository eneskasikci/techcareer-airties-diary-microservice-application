package com.eneskasikci.diaryapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diaryUsers")
public class DiaryUsers implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_diaryUsers", allocationSize = 1)
    @GeneratedValue(generator = "seq_diaryUsers", strategy = GenerationType.SEQUENCE)
    @Column(name = "diaryUser_id")
    private long userId;

    @Column(name = "diaryUser_name", nullable = false, columnDefinition = "TEXT")
    private String userName;
}
