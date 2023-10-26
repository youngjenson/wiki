package com.jens.service;

import com.jens.common.PageResp;
import com.jens.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jens.dto.CategoryEditDto;
import com.jens.dto.CategoryQueryDto;
import com.jens.dto.EbookQueryDto;
import com.jens.vo.CategoryVo;
import com.jens.vo.EbookVo;

/**
* @author 23144
* @description 针对表【category(分类)】的数据库操作Service
* @createDate 2023-10-26 23:51:45
*/
public interface CategoryService extends IService<Category> {
    PageResp<CategoryVo> list(CategoryQueryDto categoryQueryDto);

    boolean edit(CategoryEditDto categoryEditDto);

    boolean deleteById(Long id);
}
