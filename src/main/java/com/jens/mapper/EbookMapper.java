package com.jens.mapper;

import com.jens.domain.Ebook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23144
* @description 针对表【ebook(电子书)】的数据库操作Mapper
* @createDate 2023-10-23 21:26:59
* @Entity com.jens.domain.Ebook
*/
@Mapper
public interface EbookMapper extends BaseMapper<Ebook> {

}




