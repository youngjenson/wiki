package com.jens.controller;

import com.jens.common.PageResp;
import com.jens.common.R;
import com.jens.dto.CategoryEditDto;
import com.jens.dto.CategoryQueryDto;
import com.jens.dto.categoryEditDto;
import com.jens.service.CategoryService;
import com.jens.vo.CategoryVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public R list(@Valid CategoryQueryDto categoryQueryDto) {
        PageResp<CategoryVo> list = categoryService.list(categoryQueryDto);
        return R.success(list);
    }

    @PostMapping("/edit")
    public R editById(@Valid @RequestBody CategoryEditDto categoryEditDto) {
        boolean b = categoryService.edit(categoryEditDto);
        return b ? R.success() : R.failed();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteById(@PathVariable Long id) {
        boolean b = categoryService.deleteById(id);
        return b ? R.success() : R.failed();
    }
}
