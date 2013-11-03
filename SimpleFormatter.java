/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

/**
 * A simple formatter
 */
public class SimpleFormatter implements ShoppingCartFormatter{
    
    public String formatHeader(){
        total = 0;
        return " S H O P P I N G   C A R T\n\n\n";
    }
    
    public String formatLineItem(LineItem item){
        total += item.getSellPrice();
        return (String.format("%s: $%.2f\n", item.toString(), item.getSellPrice()));
    }
    
    public String formatFooter(){
        return (String.format("\n\nTOTAL DUE: $%.2f\n", total));
    }
    
    private double total;
}

