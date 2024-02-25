package com.jens.controller;

import com.jens.common.PageResp;
import com.jens.common.R;
import com.jens.dto.EbookEditDto;
import com.jens.dto.EbookQueryDto;
import com.jens.service.EbookService;
import com.jens.vo.EbookVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public R list(@Valid EbookQueryDto ebookQueryDto) {
        PageResp<EbookVo> list = ebookService.list(ebookQueryDto);
        return R.success(list);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('sys:all:admin')")
    public R editById(@Valid @RequestBody EbookEditDto ebookEditDto) {
        boolean b = ebookService.edit(ebookEditDto);
        return b ? R.success() : R.failed();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:all:admin')")
    public R deleteById(@PathVariable Long id) {
        boolean b = ebookService.deleteById(id);
        return b ? R.success() : R.failed();
    }
}
