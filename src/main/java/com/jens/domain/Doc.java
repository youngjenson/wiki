package com.jens.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 文档
 * @TableName doc
 */
@TableName(value ="doc")
@Data
public class Doc implements Serializable {
    /**
     * id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value = "id")
    private Long id;

    /**
     * 电子书id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "ebook_id")
    private Long ebookId;

    /**
     * 父id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "parent")
    private Long parent;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 顺序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 阅读数
     */
    @TableField(value = "view_count")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @TableField(value = "vote_count")
    private Integer voteCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}