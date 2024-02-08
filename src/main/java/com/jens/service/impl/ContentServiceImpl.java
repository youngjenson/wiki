package com.jens.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jens.common.R;
import com.jens.domain.Content;
import com.jens.service.ContentService;
import com.jens.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 23144
* @description 针对表【content(文档内容)】的数据库操作Service实现
* @createDate 2024-01-07 01:49:28
*/
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content>
    implements ContentService{

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public R findContentById(Long id) {

        Content content = contentMapper.selectById(id);
        String contentDetail = "";
        if(ObjectUtil.isNotEmpty(content)){
            contentDetail = content.getContent();
        }
        return R.success(contentDetail);
    }
}




