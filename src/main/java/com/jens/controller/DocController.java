package com.jens.controller;

import cn.hutool.core.lang.tree.Tree;
import com.jens.common.R;
import com.jens.dto.DocEditDto;
import com.jens.dto.DocQueryDto;
import com.jens.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    DocService docService;

    @GetMapping("/list")
    public R list(Long ebookId){
        List<Tree<String>> docTree = docService.findDocTree(ebookId);
        return R.success(docTree);
    }

    @PostMapping("/edit")
    public R editById(@Valid @RequestBody DocEditDto docEditDto) {
        boolean b = docService.edit(docEditDto);
        return b ? R.success() : R.failed();
    }

    @DeleteMapping("/delete")
    public R deleteByIds(@RequestBody List<Long> ids) {
        boolean b = docService.deleteByIds(ids);
        return b ? R.success() : R.failed();
    }
}
