package com.jens.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.jens.common.R;
import com.jens.domain.User;
import com.jens.dto.LoginUser;
import com.jens.dto.UserDto;
import com.jens.service.UserService;
import com.jens.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author:Jens
 * @Date: 2024/2/8  22:51
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public R login(UserDto user) {

        //进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(ObjectUtil.isNull(authenticate)){
            throw new RuntimeException("登陆失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getUser().getId();
        String name = loginUser.getUser().getName();
        String token = JwtUtil.createToken(id, name);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        //将用户信息保存到redis
        redisTemplate.opsForValue().set("login:"+id,loginUser);
        return R.success(map);
    }

    @Override
    public R logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //删除redis中的值
        redisTemplate.delete("login:"+loginUser.getUser().getId());
        return R.success();
    }
}
