package com.jens.controller;

import com.jens.common.R;
import com.jens.dto.UserDto;
import com.jens.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Jens
 * @Date: 2024/2/8  22:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public R selectUser(){
        return R.success();
    }

    @PostMapping("/login")
    public R login(@RequestBody UserDto user){
        return userService.login(user);
    }

    @GetMapping("logout")
    public R logout(){
        return userService.logout();
    }
}
