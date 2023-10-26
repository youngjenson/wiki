package com.jens.controller;

import com.jens.common.PageResp;
import com.jens.common.R;
import com.jens.dto.EbookDto;
import com.jens.service.EbookService;
import com.jens.vo.EbookVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public R list(EbookDto ebookDto){
        PageResp<EbookVo> list = ebookService.list(ebookDto);
        return R.success(list);
    }
}
