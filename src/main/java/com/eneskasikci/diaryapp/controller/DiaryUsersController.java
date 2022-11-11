package com.eneskasikci.diaryapp.controller;

import com.eneskasikci.diaryapp.model.DiaryUsers;
import com.eneskasikci.diaryapp.service.DiaryUsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diaryApp/users")
public class DiaryUsersController {
    private final DiaryUsersService diaryUsersService;

    public DiaryUsersController(DiaryUsersService diaryUsersService) {
        this.diaryUsersService = diaryUsersService;
    }

    // http://localhost:5555/api/diaryApp/users/saveDiaryUser
    @PostMapping("/saveDiaryUser")
    public DiaryUsers createDiaryUser(@RequestBody DiaryUsers diaryUsers){
        return diaryUsersService.saveDiaryUsers(diaryUsers);
    }

    // http://localhost:5555/api/diaryApp/users/getAllUsers
    @GetMapping("/getAllUsers")
    public List<DiaryUsers> getAllUsers(){
        return diaryUsersService.getAllDiaryUsers();
    }

}
