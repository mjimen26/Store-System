/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

import java.io.Serializable;

/**
 * A product with price and description
 */
public class Product implements Serializable{
    
    /**
     * Constructs a product
     * @param description the description
     * @param price the price
     */
    public Product(String productID, String productName,
            double invoicePrice, double sellPrice,
            int quantity, String picture,
            String description){
        this.description = description;
        this.productID=productID;
        this.productName=productName;
        this.invoicePrice=invoicePrice;
        this.sellPrice=sellPrice;
        this.quantity=quantity;
        this.picture=picture;
    }
    
    public String toString(){
        return description;
    }
    
    public double getSellPrice(){
        return sellPrice;
    }
    
    private String productID;
    private String productName;
    private double invoicePrice;
    private double sellPrice;
    private int quantity;
    private String description; //the description of a product 
    private String picture;
    
    private int sold;
}
