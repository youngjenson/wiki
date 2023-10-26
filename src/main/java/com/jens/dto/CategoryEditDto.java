package com.jens.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryEditDto {
    private Long id;

    private Long parent;

    @NotBlank(message = "[名称]不能为空")
    private String name;

    @NotNull(message = "[排序]不能为空]")
    private Integer sort;
}
