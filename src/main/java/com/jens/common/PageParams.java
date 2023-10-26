package com.jens.common;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
public class PageParams {
    //当前页码
    public Long page = 1L;

    //每页记录数
    public Long size = 100L;

    public PageParams() {
    }

    public PageParams(long page, long size) {
        this.page = page;
        this.size = size;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageParams{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}