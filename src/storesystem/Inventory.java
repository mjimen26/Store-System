
package storesystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Inventory is owned by a Seller and is composed of Products
 */
public class Inventory implements Serializable{
    
    public ArrayList<Product> inventory = new ArrayList<Product>();

    /**
     * Adds a Product in the Seller's Inventory
     * @param product a Product
     * @return true
     * @pre-condition: Inventory is empty or has Products already
     * @post-condition: A new Product is added in the Inventory
     */
    public boolean addProduct(Product product){
        inventory.add(product);
        return true;
    }
}
