package com.gp.inv.productstock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gp.inv.product.Product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @Column
    private int nrInStock = 0;

    @Column
    private boolean eligableForPromotion;

    @Column
    private Date projectedOutOfStockDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNrInStock() {
        return nrInStock;
    }

    public void setNrInStock(int nrInStock) {
        this.nrInStock = nrInStock;
    }

    public boolean isEligableForPromotion() {
        return eligableForPromotion;
    }

    public void setEligableForPromotion(boolean eligableForPromotion) {
        this.eligableForPromotion = eligableForPromotion;
    }

    public Date getProjectedOutOfStockDate() {
        return projectedOutOfStockDate;
    }

    public void setProjectedOutOfStockDate(Date projectedOutOfStockDate) {
        this.projectedOutOfStockDate = projectedOutOfStockDate;
    }
}
