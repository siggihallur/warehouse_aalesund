package com.bachelor.warehouse_one.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/inventory")
    @CrossOrigin(origins = "http://localhost:8080")
    public List<Inventory> getInventoryList(){
        return inventoryService.getInventoryList();
    }


    //TODO get inventory by id


    /**
     * Decrease inventory by qty where product_id is
     * @param productId
     * @param qty
     */
    // http://localhost:8091/inventory/purchase?product_id=9&qty=1
    @RequestMapping("/inventory/purchase")
    @CrossOrigin(origins = "http://localhost:8080")
    public void decreaseQuantity(@RequestParam("product_id") Long productId, @RequestParam("qty") int qty){
        inventoryService.decreaseQuantity(productId, qty);
    }


    //TODO addNewProductAndSetQuantity

    //TODO increase quantity

}
