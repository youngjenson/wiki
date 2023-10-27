package com.jens.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryEditDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parent;

    @NotBlank(message = "[名称]不能为空")
    private String name;

    @NotNull(message = "[排序]不能为空]")
    private Integer sort;
}
