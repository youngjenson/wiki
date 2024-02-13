package com.jens.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jens.domain.User;
import com.jens.dto.LoginUser;
import com.jens.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author:Jens
 * @Date: 2024/2/13  18:37
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getLoginName,username);
        User user = userMapper.selectOne(queryWrapper);

        if(ObjectUtil.isNull(user)){
            throw new RuntimeException("用户或者密码错误");
        }
        //todo 获取权限

        //把数据封装成UserDetails返回
        return new LoginUser(user);
    }
}
