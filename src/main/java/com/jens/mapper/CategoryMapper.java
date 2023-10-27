package com.jens.mapper;

import com.jens.domain.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jens.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 23144
* @description 针对表【category(分类)】的数据库操作Mapper
* @createDate 2023-10-26 23:51:45
* @Entity com.jens.domain.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> selectTreeNodes();
}




