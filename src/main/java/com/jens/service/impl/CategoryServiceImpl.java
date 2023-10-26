package com.jens.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jens.common.PageResp;
import com.jens.domain.Category;
import com.jens.domain.category;
import com.jens.dto.CategoryEditDto;
import com.jens.dto.CategoryQueryDto;
import com.jens.dto.categoryEditDto;
import com.jens.service.CategoryService;
import com.jens.mapper.CategoryMapper;
import com.jens.utils.CopyUtil;
import com.jens.utils.SnowflakeUtil;
import com.jens.vo.CategoryVo;
import com.jens.vo.categoryVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 23144
* @description 针对表【category(分类)】的数据库操作Service实现
* @createDate 2023-10-26 23:51:45
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    SnowflakeUtil snowflakeUtil;

    @Override
    public PageResp<CategoryVo> list(CategoryQueryDto categoryQueryDto) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        Page<Category> categoryPage = new Page<>(categoryQueryDto.page, categoryQueryDto.size);
        Page<Category> page = categoryMapper.selectPage(categoryPage, queryWrapper);
        List<Category> categories = page.getRecords();
        long total = page.getTotal();
        PageResp<CategoryVo> pageResp = new PageResp<>();
        List<CategoryVo> categoryVos = CopyUtil.copyList(categories, CategoryVo.class);
        pageResp.setTotal(total);
        pageResp.setList(categoryVos);
        return pageResp;
    }

    @Override
    public boolean edit(CategoryEditDto categoryEditDto) {
        Category category = CopyUtil.copy(categoryEditDto, Category.class);
        if (ObjectUtils.isEmpty(category.getId())) {
            long id = snowflakeUtil.nextId();
            category.setId(id);
            int insert = categoryMapper.insert(category);
            return insert > 0;
        }
        int i = categoryMapper.updateById(category);
        return i > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        int i = categoryMapper.deleteById(id);
        return i > 0;
    }
}




