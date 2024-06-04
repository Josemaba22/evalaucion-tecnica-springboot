package com.josemaba.springboot.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.josemaba.springboot.client.dto.ItemRequest;
import com.josemaba.springboot.client.entity.Item;
import com.josemaba.springboot.client.services.ItemService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<Page<Item>> getItems(Pageable pageable, @RequestBody ItemRequest itemRequest) {
        Page<Item> items;
        if(itemRequest.getName() == null || itemRequest.getName().isEmpty()) {
            items = itemService.findAll(pageable);
        } else {
            items = itemService.findByName(itemRequest.getName(), pageable);
        }
        if(items.hasContent()) {
            return ResponseEntity.ok(items);
        }
        return ResponseEntity.notFound().build();
    }
    
}
