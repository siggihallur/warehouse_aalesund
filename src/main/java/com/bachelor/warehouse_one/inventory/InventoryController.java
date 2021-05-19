package com.bachelor.warehouse_one.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * RESTful API for the store
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    /**
     * Returns inventory list for all items
     * @return
     */
    @RequestMapping("/inventory")
    @CrossOrigin(origins = "http://localhost:8080")
    public List<Inventory> getInventoryList(){
        return inventoryService.getInventoryList();
    }

    /**
     * Returns inventory status for product by id
     * localhost:8091/inventory_by_product_id?id=80
     * @param productId
     * @return
     */
    @RequestMapping("/inventory_by_product_id")
    @CrossOrigin(origins = "http://localhost:8080")
    public Inventory getInventoryById(@RequestParam("id") Long productId){
        return inventoryService.getProductInventory(productId);
    }

    /**
     * Decrease inventory by qty where product_id is
     * @param productId
     * @param qty
     */
    // http://localhost:8091/inventory/purchase?product_id=9&qty=1
    @RequestMapping("/inventory/purchase")
    @CrossOrigin(origins = "http://localhost:8080")
    @Transactional
    public boolean decreaseQuantity(@RequestParam("product_id") Long productId, @RequestParam("qty") int qty){
        return inventoryService.decreaseQuantity(productId, qty);
    }


    //TODO addNewProductAndSetQuantity
    @PostMapping("/inventory/add")
    @CrossOrigin(origins = "http://localhost:8080")
    public void addInventory(@RequestBody Inventory inventory) {
        inventoryService.addInventory(inventory);
    }

    //TODO increase or quantity

}
