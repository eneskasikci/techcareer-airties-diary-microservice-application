package com.eneskasikci.diaryapp.service;

import com.eneskasikci.diaryapp.model.DiaryUsers;
import com.eneskasikci.diaryapp.repository.IDiaryUsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryUsersService {

    private final IDiaryUsersRepository IDiaryUsersRepository;

    public DiaryUsersService(IDiaryUsersRepository IDiaryUsersRepository) {
        this.IDiaryUsersRepository = IDiaryUsersRepository;
    }

    public DiaryUsers saveDiaryUsers(DiaryUsers diaryUsers){
        return IDiaryUsersRepository.save(diaryUsers);
    }

    public List<DiaryUsers> getAllDiaryUsers(){
        return IDiaryUsersRepository.findAll();
    }

    public DiaryUsers getDiaryUserById(Long userId){
        return IDiaryUsersRepository.findById(userId).orElse(null);
    }

}
