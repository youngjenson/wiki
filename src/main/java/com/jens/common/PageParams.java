package com.jens.common;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Null;

@Getter
public class PageParams {

    //当前页码
    public Long page = 1L;

    //每页记录数
    @Max(value = 1000,message = "[每页条数]不能超过1000")
    public Long size = 1000L;

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