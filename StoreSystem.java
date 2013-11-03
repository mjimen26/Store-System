package storesystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * StoreSystem is in charge of hangling the main methods
 * in the entire application
 */
public class StoreSystem extends JFrame {
    
    //New Shopping Cart and the Interface
    final ShoppingCart cart = new ShoppingCart();
    final ShoppingCartFormatter formatter = new SimpleFormatter();
        
    //The Swing components
    final JTextArea textArea = new JTextArea(10,50);
    final JComboBox combo = new JComboBox();    
    JButton addButton = new JButton("Add");
    
    DecimalFormat df = new DecimalFormat("#.##");
    //Constructor for StoreSystem
    public StoreSystem(){
        
        //------------------------------------------------------------------
        //North Panel
        JPanel northPanel = new JPanel(new BorderLayout(10,10));
        
        ArrayList<String> productName = new ArrayList<>();
        productName.add("dragon");
        productName.add("pokemon");
        ArrayList<Double> sellPrice = new ArrayList<>();
        sellPrice.add(55.5);
        sellPrice.add(44.2);
        ArrayList<Integer> quantity = new ArrayList<>();
        quantity.add(4);
        quantity.add(22);
        
        JPanel namePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel spricePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel qtyPanel = new JPanel(new GridLayout(0, 1, 5, 5));
      
        //display the products
        for(int x=0; x<productName.size(); x++){
            JPanel name = new JPanel(new BorderLayout());
            JPanel sell = new JPanel(new BorderLayout());
            JPanel qty = new JPanel(new BorderLayout());
            
            name.add(new JLabel(productName.get(x)), BorderLayout.NORTH);
            sell.add(new JLabel("$" + sellPrice.get(x)), BorderLayout.NORTH);
            qty.add(new JLabel("Qty: " + quantity.get(x)), BorderLayout.NORTH);
            
            namePanel.add(name);
            spricePanel.add(sell);
            qtyPanel.add(qty);
        }
        
        //add the panels to northPanel
        northPanel.add(namePanel, BorderLayout.WEST);
        northPanel.add(spricePanel, BorderLayout.CENTER);
        northPanel.add(qtyPanel, BorderLayout.EAST);
        
        //------------------------------------------------------------------
        //South Panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
            //the bottom components
            JPanel ctrlPanel = new JPanel();
            ctrlPanel.add(combo);
            ctrlPanel.add(addButton);
        
            //the entire South Panel
            southPanel.add(new JScrollPane(textArea), BorderLayout.NORTH);
            southPanel.add(ctrlPanel, BorderLayout.SOUTH);
            
        //------------------------------------------------------------------    
        //change listeners
        ChangeListener listener = new 
                ChangeListener(){
                    public void stateChanged(ChangeEvent event){
                        textArea.setText(cart.format(formatter));
                    }
                };
        cart.addChangeListener(listener);
        
        //combo listener
        Product milk = new Product("Milk", 5.68);
        Product apple = new Product("Apple", 2.22);
        combo.addItem(milk);
        combo.addItem(apple);
        
        //button listener
        addButton.addActionListener(new
                ActionListener(){
                   public void actionPerformed(ActionEvent event){
                       LineItem item = (LineItem) combo.getSelectedItem();
                       cart.addItem(item);
                   } 
                });
        
        //Create Frame
        JFrame frame = new JFrame();
        //frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(northPanel,BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.setSize(600,400);
        frame.setLocation(350,200); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    //Main function
    public static void main(String[] args) {
        StoreSystem storeSys = new StoreSystem();
    }
}
