package com.jens.service;

import com.jens.common.R;
import com.jens.domain.Content;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23144
* @description 针对表【content(文档内容)】的数据库操作Service
* @createDate 2024-01-07 01:49:28
*/
public interface ContentService extends IService<Content> {

    R findContentById(Long id);
}
