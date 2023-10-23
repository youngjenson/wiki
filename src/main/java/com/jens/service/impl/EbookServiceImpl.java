package com.jens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jens.domain.Ebook;
import com.jens.service.EbookService;
import com.jens.mapper.EbookMapper;
import org.springframework.stereotype.Service;

/**
* @author 23144
* @description 针对表【ebook(电子书)】的数据库操作Service实现
* @createDate 2023-10-23 21:26:59
*/
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook>
    implements EbookService{

}




