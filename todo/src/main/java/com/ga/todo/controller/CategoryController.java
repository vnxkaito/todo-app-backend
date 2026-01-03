package com.ga.todo.controller;

import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.Item;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.service.CategoryService;
import com.ga.todo.service.ItemService;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
    CategoryService categoryService;
    ItemService itemService;

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Autowired
    public void setItemService(ItemService itemService){
        this.itemService = itemService;
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

    // --------------------------- Items

    @GetMapping("/categories/{categoryId}/items/")
    public List<Item> getItems(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling getItems ==>");
        Optional<Category> category = categoryService.getCategory(categoryId);
        if(category.isPresent()){
            return itemService.getItems(category.get());
        }else{
            throw new InformationNotFoundException("Category doesn't exist");
        }

    }

    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Optional<Item> getItem(@PathVariable(value = "categoryId") Long categoryId, @PathVariable Long itemId) {
        System.out.println("calling getItem ==>");
        Optional<Category> category = categoryService.getCategory(categoryId);
        if(category.isPresent()){
            return itemService.getItem(category.get(), itemId);
        }else{
            throw new InformationNotFoundException("Category doesn't exist");
        }
    }

    @PostMapping("/categories/{categoryId}/items/")
    public Item createItem(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Item itemObject) {
        System.out.println("calling createItem ==>");
        Optional<Category> category = categoryService.getCategory(categoryId);
        if(category.isPresent()){
            return itemService.createItem(category.get(), itemObject);
        }else{
            throw new InformationNotFoundException("Category doesn't exist");
        }
    }

    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Optional<Item> updateItem(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "itemId") Long itemId, @RequestBody Item itemObject) {
        System.out.println("calling updateItem ==>");

        Optional<Category> category = categoryService.getCategory(categoryId);
        if(category.isPresent()){
            return itemService.updateItem(category.get(), itemId, itemObject);
        }else{
            throw new InformationNotFoundException("Category doesn't exist");
        }

    }

    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public Optional<Item> deleteItem(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "itemId") Long itemId) {
        System.out.println("calling deleteItem ==>");

        Optional<Category> category = categoryService.getCategory(categoryId);
        if(category.isPresent()){
            return itemService.deleteItem(category.get(), itemId);
        }else{
            throw new InformationNotFoundException("Category doesn't exist");
        }

    }

}
