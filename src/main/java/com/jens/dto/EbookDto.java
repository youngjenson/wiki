package com.jens.dto;

import com.jens.common.PageParams;
import lombok.Data;

@Data
public class EbookDto extends PageParams {
    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;
}
