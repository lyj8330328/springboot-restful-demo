package com.example.springbootrestfuldemo.controller;


import com.example.springbootrestfuldemo.dao.CategoryDAO;
import com.example.springbootrestfuldemo.pojo.Category;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryDAO categoryDAO;

    //restful风格

    //1.返回5条记录
    @GetMapping("/category")
    public String listCategory(Model model,@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Category> page =categoryDAO.findAll(pageable);
        model.addAttribute("page",page);
        return "listCategories";
    }
    //2.保存一条记录
    @PutMapping("/category")
    public String addCategories(Category category) throws Exception {
        System.out.println("保存一条记录");
        categoryDAO.save(category);
        return "redirect:/category";
    }
    //3.删除一条记录
    @DeleteMapping("/category/{id}")
    public String deleteCategory(Category category){
        System.out.println("删除一条记录！");
        categoryDAO.delete(category);
        return "redirect:/category";
    }

    //4.更新一条记录
    @PostMapping("/category/{id}")
    public String updateCategory(Category category){
        System.out.println("更新一条记录！");
        categoryDAO.save(category);
        return "redirect:/category";
    }
    //6.跳转到编辑页
    @GetMapping("/category/{id}")
    public String addCategory(@PathVariable("id") int id, Model model) throws Exception{
        System.out.println("编辑视图");
        Category category=categoryDAO.getOne(id);
        model.addAttribute("c",category);
        return "editCategory";
    }

}
