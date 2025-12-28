package com.ga.todo.controller;

import com.ga.todo.model.Category;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/")
    public List<Category> getCategories() {
        System.out.println("calling getCategories ==>");
        return categoryService.getCategories();
    }

    @GetMapping(path = "/categories/{categoryId}")
    public Optional<Category> getCategory(@PathVariable Long categoryId) {
        System.out.println("calling getCategory ==>");
        return categoryService.getCategory(categoryId);
    }

    @PostMapping("/categories/")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("calling createCategory ==>");
        return categoryService.createCategory(categoryObject);
    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    @DeleteMapping("/categories/{categoryId}")
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        return categoryService.deleteCategory(categoryId);
    }

}
