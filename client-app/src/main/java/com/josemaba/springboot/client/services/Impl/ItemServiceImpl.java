package com.josemaba.springboot.client.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.josemaba.springboot.client.entity.Item;
import com.josemaba.springboot.client.repository.ItemRepository;
import com.josemaba.springboot.client.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	ItemRepository itemRepo;


    @Override
    public Page<Item> findAll(Pageable pageable) {
        return itemRepo.findAll(pageable);
    }

	@Override
	public void save(Item item) {
		itemRepo.save(item);
		
	}

	@Override
	public Item findById(Long id) {
		return itemRepo.findById(id).orElse(null);
	}

    public Page<Item> findByName(String name, Pageable pageable) {
        return itemRepo.findByName(name, pageable);
    }
    
}
