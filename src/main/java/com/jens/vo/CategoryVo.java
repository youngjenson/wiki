package com.jens.vo;

import com.jens.domain.Category;
import lombok.Data;

import java.util.List;

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

    /**
     * 子标签
     */
    private List<CategoryVo> children;
}
