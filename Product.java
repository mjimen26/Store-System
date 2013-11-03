/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

/**
 * A product with price and description
 */
public class Product implements LineItem{
    
    /**
     * Constructs a product
     * @param description the description
     * @param price the price
     */
    public Product(String description, double price){
        this.description = description;
        this.price = price;
    }
    
    public String toString(){
        return description;
    }
    
    public double getSellPrice(){
        return price;
    }
    
    private String description;
    private double price;

    /*
    public String productID;  //???? NOT INT???
    public String productName;
    public int quantity;
    public double invoicePrice;
    public double sellPrice;
    */
}
