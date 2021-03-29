package com.bachelor.warehouse_one.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;


    public List<Inventory> getInventoryList() {
       return inventoryRepository.findAll();
    }

    public void decreaseQuantity(Long productId, int qty) {
        inventoryRepository.decreaseQuantityByProductId(productId, qty);
    }
}
