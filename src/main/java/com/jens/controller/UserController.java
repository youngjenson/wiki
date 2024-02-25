package com.jens.controller;

import com.jens.common.R;
import com.jens.dto.UserDto;
import com.jens.dto.UserQueryDto;
import com.jens.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/list")
    public R selectUser(UserQueryDto userQueryDto) {
        return userService.selectUser(userQueryDto);
    }

    @PostMapping("/login")
    public R login(@RequestBody UserDto user) {
        return userService.login(user);
    }

    @GetMapping("logout")
    public R logout() {
        return userService.logout();
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('sys:all:admin')")
    public R deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('sys:all:admin')")
    public R save(@RequestBody UserQueryDto queryDto) {
        return userService.save(queryDto);
    }
}
