package com.jens.mapper;

import com.jens.domain.Doc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23144
* @description 针对表【doc(文档)】的数据库操作Mapper
* @createDate 2023-10-27 22:06:19
* @Entity com.jens.domain.Doc
*/
@Mapper
public interface DocMapper extends BaseMapper<Doc> {

}




