package com.codeuptopia.secondhandmarket.service;

import com.codeuptopia.secondhandmarket.dao.ItemDao;
import com.codeuptopia.secondhandmarket.model.Item;
import com.codeuptopia.secondhandmarket.model.User;
import com.google.inject.Inject;

import java.util.List;

public class ItemService {
    
    private ItemDao itemDao;
    
    @Inject
    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
    
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }
    
    public Item getItemById(long itemId) {
        return itemDao.getItemById(itemId);
    }
    
    public void addItem(Item item) {
        itemDao.addItem(item);
        itemDao.indexItem(item);
    }
    
    public void updateItem(Item item) {
        itemDao.updateItem(item);
        itemDao.indexItem(item);
    }
    
    public void deleteItem(long itemId) {
        itemDao.deleteItem(itemId);
        // TODO: remove item from ElasticSearch index
    }
    
    public List<Item> getItemsBySeller(User seller) {
        // TODO: implement logic for retrieving items by seller
        return null;
    }
    
    public List<Item> searchItems(String query) {
        return itemDao.searchItems(query);
    }
    
    public double getAverageItemPrice() {
        List<Item> items = itemDao.getAllItems();
        if (items.isEmpty()) {
            return 0.0;
        } else {
            double sum = 0.0;
            for (Item item : items) {
                sum += item.getPrice();
            }
            return sum / items.size();
        }
    }
    
    // TODO: add additional methods for computing analytics or performing other business logic
}
