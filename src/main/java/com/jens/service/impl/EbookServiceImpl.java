package com.jens.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jens.common.PageResp;
import com.jens.domain.Ebook;
import com.jens.dto.EbookEditDto;
import com.jens.dto.EbookQueryDto;
import com.jens.mapper.EbookMapper;
import com.jens.service.EbookService;
import com.jens.utils.CopyUtil;
import com.jens.utils.SnowflakeUtil;
import com.jens.vo.EbookVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 23144
 * @description 针对表【ebook(电子书)】的数据库操作Service实现
 * @createDate 2023-10-23 21:26:59
 */
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook>
        implements EbookService {

    @Resource
    EbookMapper ebookMapper;

    @Resource
    SnowflakeUtil snowflakeUtil;


    @Override
    public PageResp<EbookVo> list(EbookQueryDto ebookQueryDto) {

        LambdaQueryWrapper<Ebook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(ebookQueryDto.getName()), Ebook::getName, ebookQueryDto.getName());
        Page<Ebook> pageInfo = new Page<>(ebookQueryDto.page, ebookQueryDto.size);
        Page<Ebook> ebookPage = ebookMapper.selectPage(pageInfo, queryWrapper);
        List<Ebook> ebooks = ebookPage.getRecords();
        long total = ebookPage.getTotal();
        PageResp<EbookVo> pageResp = new PageResp<>();
        List<EbookVo> ebookVos = CopyUtil.copyList(ebooks, EbookVo.class);
        pageResp.setTotal(total);
        pageResp.setList(ebookVos);
        return pageResp;
    }

    @Override
    public boolean edit(EbookEditDto ebookEditDto) {
        Ebook ebook = CopyUtil.copy(ebookEditDto, Ebook.class);
        if (ObjectUtils.isEmpty(ebook.getId())) {
            long id = snowflakeUtil.nextId();
            ebook.setId(id);
            int insert = ebookMapper.insert(ebook);
            return insert > 0;
        }
        int i = ebookMapper.updateById(ebook);
        return i > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        int i = ebookMapper.deleteById(id);
        return i > 0;
    }
}




