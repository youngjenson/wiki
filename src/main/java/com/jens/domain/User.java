package com.jens.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author:Jens
 * @Date: 2024/2/8  22:53
 * @Version 1.0
 */

@Data
@TableName("user")
public class User implements Serializable {

    @TableId
    private Long id;
    private String loginName;
    private String name;
    private String password;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
