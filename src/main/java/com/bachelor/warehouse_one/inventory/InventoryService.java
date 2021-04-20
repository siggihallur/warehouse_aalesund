package com.bachelor.warehouse_one.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     *
     * @return
     */
    public List<Inventory> getInventoryList() {
       return inventoryRepository.findAll();
    }

    /**
     *
     * @param productId
     * @param qty
     * @return
     */
    public boolean decreaseQuantity(Long productId, int qty) {
        boolean decreaseSuccessful = false;

        if( (inventoryRepository.findInventoryByProduct_id(productId).getQuantity() >= qty) && (qty > 0)) {
            inventoryRepository.decreaseQuantityByProductId(productId, qty);
            if(inventoryRepository.findInventoryByProduct_id(productId).getQuantity() == qty){
                inventoryRepository.setInStockToFalse(productId);
            }
            decreaseSuccessful = true;
        }
        return decreaseSuccessful;
    }

    public Inventory getProductInventory(Long productId) {
        return inventoryRepository.findInventoryByProduct_id(productId);
    }

    public void addInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }
}
