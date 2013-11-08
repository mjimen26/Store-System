/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Administrator
 */
public class Inventory implements Serializable{
    
    public ArrayList<Product> inventory = new ArrayList<Product>();

    public boolean addProduct(Product product){
    inventory.add(product);
    return true;
}
    
    public Inventory(){}
    
    
    public void searchInventory(){
        if (!inventory.isEmpty()){
            Iterator it=inventory.iterator();
            while (it.hasNext()){
                
            }
        }
    }
    
    public boolean updateQuantity(Product product, int newQuantity){
        if (!inventory.isEmpty()){
            Iterator it=inventory.iterator();
            Product current;
            while (it.hasNext()){
                current=(Product)it.next();
               // if (current==product) current.quantity=newQuantity;
                return true;
            }
        }
        return false;
    }

    
}
