package com.ga.todo.service;

import com.ga.todo.exception.InformationExistException;
import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.Item;
import com.ga.todo.repository.CategoryRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategory(Long categoryId){
        System.out.println("Service calling getCategory");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            return category;
        }else{
            throw new InformationNotFoundException("Category '"+categoryId+"' was not found");
        }
    }

    public List<Category> getCategories(){
        System.out.println("Service calling getCategories");
        return categoryRepository.findAll();
    }

    public Category createCategory(Category categoryObject){
        System.out.println("Service calling createCategory");

        Optional<Category> category = categoryRepository.findById(categoryObject.getCategoryId());
        if(category.isPresent()){
            throw new InformationExistException("Category with the id '"+categoryObject.getCategoryId()+"' already exists");
        }else{
            Category createCategory = new Category();
            createCategory.setName(categoryObject.getName());
            createCategory.setDescription(categoryObject.getDescription());
            return categoryRepository.save(createCategory);
        }
    }

    public Category updateCategory(Long categoryId, Category categoryObject){
        System.out.println("Service calling updateCategory");

        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new InformationNotFoundException("Category with the id '"+categoryObject.getName()+"' doesn't exists");
        }else{
            Category categoryUpdate = category.get();
            categoryUpdate.setName(categoryObject.getName());
            categoryUpdate.setDescription(categoryObject.getDescription());
            return categoryRepository.save(categoryUpdate);
        }
    }

    public Optional<Category> deleteCategory(Long categoryId){
        System.out.println("Service calling deleteCategory");

        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new InformationNotFoundException("Category with the id '"+categoryId+"' doesn't exists");
        }else{
            categoryRepository.deleteById(categoryId);
            return category;
        }
    }

}
