package com.jens.vo;

import lombok.Data;

@Data
public class CategoryVo {

    /**
     * id
     */
    private Long id;

    /**
     * 父id
     */
    private Long parent;

    /**
     * 名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer sort;
}
