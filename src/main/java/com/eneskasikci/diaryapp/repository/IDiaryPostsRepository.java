package com.eneskasikci.diaryapp.repository;

import com.eneskasikci.diaryapp.model.DiaryPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDiaryPostsRepository extends JpaRepository<DiaryPosts, Long> {
    List<DiaryPosts> findAllByDiaryUsers_UserId(Long userId);

    List<DiaryPosts> findAllByDiaryUsers_UserName(String userName);
}

