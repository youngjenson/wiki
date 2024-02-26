package com.jens.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class UserVo {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String loginName;

    private String name;

    private String token;
}
