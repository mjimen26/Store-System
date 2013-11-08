/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;
import java.util.*;
import javax.swing.event.*;

/**
 * A Shopping Cart for a sale, consisting of line items;
 */
public class ShoppingCart {

    /**
     * Constructs a blank shopping cart
     */
    public ShoppingCart(){
        items = new ArrayList<LineItem>();
        listeners = new ArrayList<ChangeListener>();
    }
    
    /**
     * Adds an item to the invoice
     * @param item the item to add
     */
    public void addItem(LineItem item){
        items.add(item);
        //Notify all observers of the change to the shopping cart
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners)
            listener.stateChanged(event);
    }
    
    /**
     * Adds a ChangeListener to the shopping cart
     * @param listener the change listener to add
     */
    public void addChangeListener(ChangeListener listener){
        listeners.add(listener);
    }
    
    /**
     * Gets an iterator that iterates through the items
     * @return an iterator for the items
     */
    public Iterator<LineItem> getItems(){
        return new
        Iterator<LineItem>(){
            public boolean hasNext(){
                return current < items.size();
            }
            
            public LineItem next(){
                return items.get(current++);
            }
            
            public void remove(){
                throw new UnsupportedOperationException();
            }
            
            private int current = 0;
        };
    }
    
    public String format(ShoppingCartFormatter formatter){
        String r = formatter.formatHeader();
        Iterator<LineItem> iter = getItems();
        while(iter.hasNext())
            r += formatter.formatLineItem(iter.next());
        return r + formatter.formatFooter();
    }
    
    private ArrayList<LineItem> items;
    private ArrayList<ChangeListener> listeners;
}
