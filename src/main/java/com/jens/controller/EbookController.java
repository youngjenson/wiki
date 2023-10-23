package com.jens.controller;

import com.jens.common.R;
import com.jens.domain.Ebook;
import com.jens.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public R<List<Ebook>> list(){
        List<Ebook> list = ebookService.list();
        return R.success(list);
    }
}
