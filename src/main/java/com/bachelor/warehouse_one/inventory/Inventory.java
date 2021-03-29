package com.bachelor.warehouse_one.inventory;

import javax.persistence.*;

@Entity(name = "Inventory")
@Table(
        name = "inventory",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "inventory_product_id_unique",
                        columnNames = "product_id"
                )
        }
)
public class Inventory {

    @Id
    @SequenceGenerator(
            name = "inventory_sequence",
            sequenceName = "inventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_sequence"
    )
    private Long id;

    @Column(
            name = "product_id",
            nullable = false,
            columnDefinition = "NUMERIC"
    )
    private Long product_id;

    @Column(
            name = "in_stock",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean in_stock;

    @Column(
            name = "quantity",
            nullable = false,
            columnDefinition = "NUMERIC"
    )
    private int quantity;

    public Inventory() {
    }

    public Inventory(Long product_id, boolean in_stock, int quantity) {
        this.product_id = product_id;
        this.in_stock = in_stock;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public boolean isIn_stock() {
        return in_stock;
    }

    public void setIn_stock(boolean in_stock) {
        this.in_stock = in_stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", in_stock=" + in_stock +
                ", quantity=" + quantity +
                '}';
    }
}
