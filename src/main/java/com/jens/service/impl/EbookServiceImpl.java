package com.jens.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jens.domain.Ebook;
import com.jens.dto.EbookDto;
import com.jens.service.EbookService;
import com.jens.mapper.EbookMapper;
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
    public List<EbookVo> list(EbookDto ebookDto) {
        String name = ebookDto.getName();
        Long id = ebookDto.getId();
        LambdaQueryWrapper<Ebook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name),Ebook::getName,name);
        List<Ebook> ebooks = ebookMapper.selectList(queryWrapper);
        List<EbookVo> ebookVos = CopyUtil.copyList(ebooks, EbookVo.class);
        return ebookVos;
    }
}




