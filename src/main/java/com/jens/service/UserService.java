package com.jens.service;

import com.jens.common.R;
import com.jens.domain.User;
import com.jens.dto.UserDto;
import com.jens.dto.UserQueryDto;

/**
 * @Author:Jens
 * @Date: 2024/2/8  22:51
 * @Version 1.0
 */
public interface UserService {
    R login(UserDto user);

    R logout();

    R selectUser(UserQueryDto userQueryDto);

    R deleteById(Long id);

    R save(UserQueryDto queryDto);
}
