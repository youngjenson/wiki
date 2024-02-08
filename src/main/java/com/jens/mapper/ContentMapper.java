package com.jens.mapper;

import com.jens.domain.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23144
* @description 针对表【content(文档内容)】的数据库操作Mapper
* @createDate 2024-01-07 01:49:28
* @Entity com.jens.domain.Content
*/
@Mapper
public interface ContentMapper extends BaseMapper<Content> {

}




