package com.jens.controller;

import com.jens.common.PageResp;
import com.jens.common.R;
import com.jens.dto.EbookEditDto;
import com.jens.dto.EbookQueryDto;
import com.jens.service.EbookService;
import com.jens.vo.EbookVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public R list(EbookQueryDto ebookQueryDto) {
        PageResp<EbookVo> list = ebookService.list(ebookQueryDto);
        return R.success(list);
    }

    @PostMapping("/edit")
    public R editById(@RequestBody EbookEditDto ebookEditDto) {
        boolean b = ebookService.editById(ebookEditDto);
        return b ? R.success() : R.failed();
    }
}
