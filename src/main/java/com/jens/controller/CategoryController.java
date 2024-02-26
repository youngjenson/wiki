package com.jens.controller;

import com.jens.common.R;
import com.jens.dto.CategoryEditDto;
import com.jens.service.CategoryService;
import com.jens.vo.CategoryVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public R list() {
        List<CategoryVo> list = categoryService.findCategoryTree();
        return R.success(list);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('sys:all:admin')")
    public R editById(@Valid @RequestBody CategoryEditDto categoryEditDto) {
        boolean b = categoryService.edit(categoryEditDto);
        return b ? R.success() : R.failed();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:all:admin')")
    public R deleteById(@PathVariable Long id) {
        boolean b = categoryService.deleteById(id);
        return b ? R.success() : R.failed();
    }
}
