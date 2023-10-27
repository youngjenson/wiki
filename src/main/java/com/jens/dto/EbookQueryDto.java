package com.jens.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jens.common.PageParams;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EbookQueryDto extends PageParams {
    /**
     * id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 名称
     */
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long category2Id;
}
