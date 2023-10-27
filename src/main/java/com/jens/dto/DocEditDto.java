package com.jens.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class DocEditDto {

    /**
     * id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 电子书id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "[电子书ID]不能为空")
    private Long ebookId;

    /**
     * 父id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "[父ID]不能为空")
    private Long parent;

    /**
     * 名称
     */
    @NotBlank(message = "[名称]不能为空")
    private String name;

    /**
     * 顺序
     */
    @NotNull(message = "[排序]不能为空]")
    private Integer sort;

    /**
     * 阅读数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer voteCount;
}
