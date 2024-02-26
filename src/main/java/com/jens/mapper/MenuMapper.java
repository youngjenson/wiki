package com.jens.mapper;

import com.jens.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 23144
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2024-02-15 13:59:06
* @Entity com.jens.domain.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

}




