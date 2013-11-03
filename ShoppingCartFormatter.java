/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

/**
 * Describes the tasks that the Shopping Cart formatter
 * needs to carry out
 */
public interface ShoppingCartFormatter {
    /**
     * Formats the header of the Shopping Cart
     * @return the header
     */
    String formatHeader();
    
    /**
     * Formats the product of the shopping cart
     * @return formatted product
     */
    String formatLineItem(LineItem item);
    
    /**
     * Formats the footer of the Shopping Cart
     * @return the footer
     */
    String formatFooter();
}

