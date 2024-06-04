package com.josemaba.springboot.client.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.josemaba.springboot.client.entity.Item;

public interface ItemService {

	Page<Item> findAll(Pageable pageable);
	
	public void save (Item user);
	
	public Item findById(Long id);
	
	public Page<Item> findByName(String name, Pageable pageable);
    
}
