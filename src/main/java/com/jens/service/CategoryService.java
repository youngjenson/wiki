package com.jens.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jens.domain.Category;
import com.jens.dto.CategoryEditDto;
import com.jens.vo.CategoryVo;

import java.util.List;

/**
* @author 23144
* @description 针对表【category(分类)】的数据库操作Service
* @createDate 2023-10-26 23:51:45
*/
public interface CategoryService extends IService<Category> {

    boolean edit(CategoryEditDto categoryEditDto);

    boolean deleteById(Long id);

    List<CategoryVo> findCategoryTree();
}
