package com.jens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jens.domain.Menu;
import com.jens.service.MenuService;
import com.jens.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 23144
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2024-02-15 13:59:06
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




