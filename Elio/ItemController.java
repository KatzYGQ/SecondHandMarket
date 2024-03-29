package com.codeuptopia.secondhandmarket.controller;

import com.google.inject.Inject;
import com.codeuptopia.secondhandmarket.model.Item;
import com.codeuptopia.secondhandmarket.service.ItemService;
import io.javalin.http.Context;

import java.util.List;

public class ItemController {
    
    private final ItemService itemService;
    
    @Inject
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    
    public void getItem(Context ctx) {
        String itemId = ctx.pathParam("id");
        Item item = itemService.getItemById(itemId);
        if (item != null) {
            ctx.json(item);
        } else {
            ctx.status(404);
        }
    }
    
    public void getAllItems(Context ctx) {
        List<Item> items = itemService.getAllItems();
        ctx.json(items);
    }
    
    public void addItem(Context ctx) {
        Item item = ctx.bodyAsClass(Item.class);
        itemService.addItem(item);
        ctx.status(201);
    }
    
    public void updateItem(Context ctx) {
        String itemId = ctx.pathParam("id");
        Item item = ctx.bodyAsClass(Item.class);
        item.setId(itemId);
        itemService.updateItem(item);
        ctx.status(204);
    }
    
    public void deleteItem(Context ctx) {
        String itemId = ctx.pathParam("id");
        itemService.deleteItem(itemId);
        ctx.status(204);
    }
    
    // frontend API endpoints
    // These endpoints handle HTTP requests with specific query parameters or path parameters and return JSON data in response.
    
    public void getItemById(Context ctx) {
        String itemId = ctx.pathParam("id");
        Item item = itemService.getItemById(itemId);
        if (item != null) {
            ctx.json(item);
        } else {
            ctx.status(404);
        }
    }
    
    public void getAllItemsByCategory(Context ctx) {
        String category = ctx.pathParam("category");
        List<Item> items = itemService.getItemsByCategory(category);
        ctx.json(items);
    }
    
    public void searchItems(Context ctx) {
        String query = ctx.queryParam("q");
        List<Item> items = itemService.searchItems(query);
        ctx.json(items);
    }
    
}

