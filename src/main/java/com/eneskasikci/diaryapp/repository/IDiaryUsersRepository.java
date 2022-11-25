package com.eneskasikci.diaryapp.repository;

import com.eneskasikci.diaryapp.model.DiaryUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiaryUsersRepository extends JpaRepository<DiaryUsers, Long> {

    DiaryUsers findByUserName(String userName);
}
