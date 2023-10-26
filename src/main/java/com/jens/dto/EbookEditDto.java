package com.jens.dto;

import lombok.Data;

@Data
public class EbookEditDto {
    /**
     * id
     */
    private Long id;

    /**
     * 封面
     */
    private String cover;

    /**
     * 名称
     */
    private String name;

    /**
     * 分类1
     */
    private Long category1Id;

    /**
     * 分类2
     */
    private Long category2Id;

    /**
     * 描述
     */
    private String description;
}
