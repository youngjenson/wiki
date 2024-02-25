package com.jens.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jens.common.PageResp;
import com.jens.common.R;
import com.jens.domain.User;
import com.jens.dto.LoginUser;
import com.jens.dto.UserDto;
import com.jens.dto.UserQueryDto;
import com.jens.exception.BusinessException;
import com.jens.exception.BusinessExceptionCode;
import com.jens.mapper.UserMapper;
import com.jens.service.UserService;
import com.jens.utils.CopyUtil;
import com.jens.utils.JwtUtil;
import com.jens.utils.SnowflakeUtil;
import com.jens.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Jens
 * @Date: 2024/2/8  22:51
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SnowflakeUtil snowflakeUtil;

    @Override
    public R login(UserDto user) {

        log.info("进行用户认证");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getLoginName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (ObjectUtil.isNull(authenticate)) {
            throw new RuntimeException("登陆失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getUser().getId();
        String token = JwtUtil.createToken(id);

        log.info("将用户信息保存到redis");
        redisTemplate.opsForValue().set("login:" + id, loginUser, 30, TimeUnit.MINUTES);
        UserVo userVo = CopyUtil.copy(loginUser.getUser(), UserVo.class);
        userVo.setToken(token);
        return R.success(userVo);
    }

    @Override
    public R logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //删除redis中的值
        redisTemplate.delete("login:" + loginUser.getUser().getId());
        return R.success();
    }

    @Override
    public R selectUser(UserQueryDto userQueryDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(userQueryDto.getName()), User::getName, userQueryDto.getName());
        Page<User> page = new Page<>(userQueryDto.getPage(), userQueryDto.size);
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        List<User> users = userPage.getRecords();
        long total = userPage.getTotal();
        PageResp<UserVo> pageResp = new PageResp<>();
        List<UserVo> userVos = CopyUtil.copyList(users, UserVo.class);
        pageResp.setList(userVos);
        pageResp.setTotal(total);
        return R.success(pageResp);
    }

    @Override
    public R deleteById(Long id) {
        if (id == null) {
            return R.failed("id不能为空");
        }
        int i = userMapper.deleteById(id);
        if (i >= 0) {
            return R.success();
        }
        return R.failed();
    }

    @Override
    public R save(UserQueryDto queryDto) {
        User user = CopyUtil.copy(queryDto, User.class);
        if (ObjectUtil.isEmpty(user.getId())) {
            if (ObjectUtil.isEmpty(selectByLoginName(user.getLoginName()))) {
                user.setId(snowflakeUtil.nextId());
                int insert = userMapper.insert(user);
                if (insert < 0) {
                    return R.failed("新增失败");
                }
            } else {
                //用户名已存在
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }

        } else {
            user.setLoginName(null);
            user.setPassword(null);
            int i = userMapper.updateByIdSelective(user);
            if (i < 0) {
                return R.failed("更新失败");
            }
        }
        return R.success();
    }

    public User selectByLoginName(String loginName) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getLoginName, loginName));
    }
}