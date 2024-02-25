package com.jens.dto;

import com.jens.common.PageParams;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryDto extends PageParams {

    private Long id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;
}
