package com.jens.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jens.domain.Doc;
import com.jens.dto.DocEditDto;
import com.jens.dto.DocQueryDto;
import com.jens.mapper.DocMapper;
import com.jens.service.DocService;
import com.jens.utils.CopyUtil;
import com.jens.utils.SnowflakeUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 23144
 * @description 针对表【doc(文档)】的数据库操作Service实现
 * @createDate 2023-10-27 22:06:19
 */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc>
        implements DocService {

    @Resource
    DocMapper docMapper;

    @Resource
    SnowflakeUtil snowflakeUtil;

    @Override
    public List<Tree<String>> findDocTree(Long ebookId) {

        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotEmpty(ebookId), Doc::getEbookId, ebookId);
        List<Doc> docs = docMapper.selectList(queryWrapper);
        List<Doc> copyDocs = CollUtil.newArrayList();
        copyDocs.addAll(docs);
        String parent = "0";
        TreeNodeConfig nodeConfig = new TreeNodeConfig();
        nodeConfig.setIdKey("id");
        nodeConfig.setDeep(10);
        return TreeUtil.build(copyDocs, parent, nodeConfig,
                (node, tree) -> {
                    tree.setId(node.getId().toString());
                    tree.setName(node.getName());
                    tree.setParentId(node.getParent().toString());
                    tree.putExtra("parent", node.getParent().toString());
                    tree.putExtra("sort", node.getSort());
                    tree.putExtra("ebookId", node.getEbookId());
                });
    }

    @Override
    public boolean edit(DocEditDto docEditDto) {
        Doc doc = CopyUtil.copy(docEditDto, Doc.class);
        if (ObjectUtils.isEmpty(doc.getId())) {
            long id = snowflakeUtil.nextId();
            doc.setId(id);
            int insert = docMapper.insert(doc);
            return insert > 0;
        }
        int i = docMapper.updateById(doc);
        return i > 0;
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        int i = docMapper.deleteBatchIds(ids);
        return i > 0;
    }
}




