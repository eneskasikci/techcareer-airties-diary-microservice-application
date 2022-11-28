package com.eneskasikci.diaryapp.controller;

import com.eneskasikci.diaryapp.model.DiaryUsers;
import com.eneskasikci.diaryapp.service.DiaryUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Diary Users", value = "Diary User Controller API Documentation")
@RequestMapping("/api/diaryApp/users")
public class DiaryUsersController {
    private final DiaryUsersService diaryUsersService;

    public DiaryUsersController(DiaryUsersService diaryUsersService) {
        this.diaryUsersService = diaryUsersService;
    }

    // http://localhost:5555/api/diaryApp/users/saveDiaryUser
    @PostMapping("/saveDiaryUser")
    @ApiOperation(value = "Creates a new Diary User in the database with a User Request parameters." +
            "Passing a JSON body.", notes = "This is not a must. When Post Create request executes we are checking if the user exists or not." +
            "If it doesn't we are automatically saving that user in the database.")
    public DiaryUsers createDiaryUser(@RequestBody @ApiParam(value = "Requirements for the user creation in the database.") DiaryUsers diaryUsers){
        return diaryUsersService.saveDiaryUsers(diaryUsers);
    }

    // http://localhost:5555/api/diaryApp/users/getAllUsers
    @GetMapping("/getAllUsers")
    @ApiOperation(value = "To see every user in the diary database.")
    public List<DiaryUsers> getAllUsers(){
        return diaryUsersService.getAllDiaryUsers();
    }

}
