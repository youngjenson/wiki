package com.jens.service;

import com.jens.common.PageResp;
import com.jens.domain.Ebook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jens.dto.EbookEditDto;
import com.jens.dto.EbookQueryDto;
import com.jens.vo.EbookVo;

/**
* @author 23144
* @description 针对表【ebook(电子书)】的数据库操作Service
* @createDate 2023-10-23 21:26:59
*/
public interface EbookService extends IService<Ebook> {
    PageResp<EbookVo> list(EbookQueryDto ebookQueryDto);

    boolean editById(EbookEditDto ebookEditDto);
}
