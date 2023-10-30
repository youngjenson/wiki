package com.jens.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jens.domain.Doc;
import com.jens.dto.DocEditDto;
import com.jens.dto.DocQueryDto;

import java.util.List;

/**
* @author 23144
* @description 针对表【doc(文档)】的数据库操作Service
* @createDate 2023-10-27 22:06:19
*/
public interface DocService extends IService<Doc> {
    List<Tree<String>> findDocTree(Long ebookId);

    boolean edit(DocEditDto docEditDto);

    boolean deleteByIds(List<Long> ids);
}
