package com.ga.todo.service;

import com.ga.todo.exception.InformationExistException;
import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.Item;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Optional<Item> getItem(Category category, Long itemId){
        System.out.println("Service calling getItem ==>");
        Optional<Item> item = this.itemRepository.findByCategoryAndId(category, itemId);
        if(item.isPresent()){
            return item;
        }else{
            throw new InformationNotFoundException("No item with the name " + itemId + " was found");
        }
    }

    public List<Item> getItems(Category category){
        System.out.println("Service calling getItems ==>");
        return this.itemRepository.findByCategory(category);
    }

    public Item createItem(Category category, Item itemObject){
        System.out.println("Service calling createItem ==>");
        Optional<Item> item = this.itemRepository.findByCategoryAndId(category, itemObject.getId());
        if(item.isEmpty()){
            itemObject.setCategory(category);
            this.itemRepository.save(itemObject);
            return itemObject;
        }else {
            throw new InformationExistException("An item with the ID "+itemObject.getId()+" already exists");
        }
    }

    public Optional<Item> deleteItem(Category category, Long itemId){
        System.out.println("Service calling deleteItem ==>");
        Optional<Item> item = this.itemRepository.findByCategoryAndId(category, itemId);
        if(item.isPresent()){
            this.itemRepository.delete(item.get());
            return item;
        }else{
            throw new InformationNotFoundException("No item with the name " + itemId + " was found");
        }
    }

    public Optional<Item> updateItem(Category category, Long itemId, Item item){
        System.out.println("Service calling updateItem ==>");
        Optional<Item> updateItem = this.itemRepository.findByCategoryAndId(category, itemId);
        if(updateItem.isPresent()){
            Item updateItemObject = updateItem.get();
            updateItemObject.setName(item.getName());
            updateItemObject.setDescription(item.getDescription());
            return Optional.of(this.itemRepository.save(updateItemObject));
        }else{
            throw new InformationNotFoundException("No item with the name " + itemId + " was found");
        }
    }

}
