package com.codeuptopia.secondhandmarket.dao;

import java.util.List;

import com.codeuptopia.secondhandmarket.model.Item;

public interface ItemDao {
    
    public void addItem(Item item);
    
    public void updateItem(Item item);
    
    public void deleteItem(long itemId);
    
    public Item getItemById(long itemId);
    
    public List<Item> getAllItems();
    
    public void indexItem(Item item);
    
    public List<Item> searchItems(String query);
    
}
