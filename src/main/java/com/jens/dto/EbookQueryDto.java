package com.jens.dto;

import com.jens.common.PageParams;
import lombok.Getter;

@Getter
public class EbookQueryDto extends PageParams {
    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EbookQueryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
