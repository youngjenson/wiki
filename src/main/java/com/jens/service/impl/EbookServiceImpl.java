package com.jens.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jens.common.PageResp;
import com.jens.domain.Ebook;
import com.jens.dto.EbookDto;
import com.jens.mapper.EbookMapper;
import com.jens.service.EbookService;
import com.jens.utils.CopyUtil;
import com.jens.vo.EbookVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 23144
* @description 针对表【ebook(电子书)】的数据库操作Service实现
* @createDate 2023-10-23 21:26:59
*/
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook>
    implements EbookService{

    @Resource
    EbookMapper ebookMapper;


    @Override
    public PageResp<EbookVo> list(EbookDto ebookDto) {
        String name = ebookDto.getName();
        Long id = ebookDto.getId();
        LambdaQueryWrapper<Ebook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name),Ebook::getName,name);

        Page<Ebook> pageInfo = new Page<>(ebookDto.getPageNo(), ebookDto.getPageSize());
        Page<Ebook> ebookPage = ebookMapper.selectPage(pageInfo, queryWrapper);
        List<Ebook> ebooks = ebookPage.getRecords();
        long total = ebookPage.getTotal();
        PageResp<EbookVo> pageResp = new PageResp<>();
        List<EbookVo> ebookVos = CopyUtil.copyList(ebooks, EbookVo.class);
        pageResp.setTotal(total);
        pageResp.setList(ebookVos);
        return pageResp;
    }
}




