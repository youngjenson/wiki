package com.jens.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jens.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:Jens
 * @Date: 2024/2/8  22:52
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
}
