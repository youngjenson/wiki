package com.jens.service;

import com.jens.domain.Ebook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jens.dto.EbookDto;
import com.jens.vo.EbookVo;

import java.util.List;

/**
* @author 23144
* @description 针对表【ebook(电子书)】的数据库操作Service
* @createDate 2023-10-23 21:26:59
*/
public interface EbookService extends IService<Ebook> {
    public List<EbookVo> list(EbookDto ebookDto);
}
