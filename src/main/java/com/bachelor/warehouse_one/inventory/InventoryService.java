package com.bachelor.warehouse_one.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     * Gets list of all inventory from database
     * @return
     */
    public List<Inventory> getInventoryList() {
       return inventoryRepository.findAll();
    }

    /**
     * Reduces quantity of product by its id and by given quantity.
     * @param productId
     * @param qty
     * @return true if successfully reduces quantity
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

    /**
     * Gets inventory for product by id
     * @param productId
     * @return
     */
    public Inventory getProductInventory(Long productId) {
        return inventoryRepository.findInventoryByProduct_id(productId);
    }

    /**
     * Adds inventory to database
     * @param inventory
     */
    public void addInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }
}
