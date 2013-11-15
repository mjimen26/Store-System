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
public class StoreSystem extends JPanel{
    
    //New Shopping Cart and the Interface
    final ShoppingCart cart = new ShoppingCart();
    final ShoppingCartFormatter formatter = new SimpleFormatter();
        
    //The Main Panel
    private JPanel mainPanel = new JPanel();
    
    //Constructor for StoreSystem
    public StoreSystem(){
        JPanel centerPanel = createCenterPanel();
        JPanel southPanel = createSouthPanel();
        
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(centerPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        
    }
    
    private JPanel createCenterPanel(){
        //North Panel
        JPanel centerPanel = new JPanel(new GridLayout(0,4));
        DecimalFormat df = new DecimalFormat("#.##");
        
        String[] productName = 
        {
            "Minecraft Pickaxe", "Diablo 3 XBOX", "GTA V PS3", "Batman Mask", 
            "Pikachu Plushie", "Halo Suit", "Mario Mustache"
        };
        
        double[] sellPrice =
        {
            150.92, 50.99, 59.99, 80.42, 
            253.67, 999.01, 5.50
        };
        
        int[] quantity =
        {
            2,3,4,2,3,3,1
        };
        
        JPanel checkPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel namePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel spricePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel qtyPanel = new JPanel(new GridLayout(0, 1, 5, 5));
      
        //display the products
        for(int x=0; x<productName.length; x++){
            JPanel check = new JPanel(new BorderLayout());
            JPanel name = new JPanel(new BorderLayout());
            JPanel sell = new JPanel(new BorderLayout());
            JPanel qty = new JPanel(new BorderLayout());
            
            check.add(new JCheckBox());
            name.add(new JLabel(productName[x]), BorderLayout.NORTH);
            sell.add(new JLabel("$" + sellPrice[x]), BorderLayout.NORTH);
            qty.add(new JLabel("Qty: " + quantity[x]), BorderLayout.NORTH);
            
            checkPanel.add(check);
            namePanel.add(name);
            spricePanel.add(sell);
            qtyPanel.add(qty);
        }
        
        //add the panels to northPanel
        centerPanel.add(checkPanel);
        centerPanel.add(namePanel);
        centerPanel.add(spricePanel);
        centerPanel.add(qtyPanel);
        
        return centerPanel;
    }
    
    private JPanel createSouthPanel(){
        //South Panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea(10,50);
        final JComboBox combo = new JComboBox();    
        JButton addButton = new JButton("Add");
    
            //the bottom components
            JPanel ctrlPanel = new JPanel();
            ctrlPanel.add(combo);
            ctrlPanel.add(addButton);
        
            //the entire South Panel
            southPanel.add(new JScrollPane(textArea), BorderLayout.NORTH);
            southPanel.add(ctrlPanel, BorderLayout.SOUTH);
            
            return southPanel;
    }
    
    public JPanel getMainPanel()
    {
        return mainPanel;
    }
 
    private static void createAndShowUI()
    {
        JFrame frame = new JFrame("Layout Example");
        frame.getContentPane().add(new StoreSystem().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    //Main function
    public static void main(String[] args) {
        User customer=new Customer("Marvin","cardei");
        Seller seller1=new Seller("one","password");
        Seller seller2=new Seller("two","password");
        
       //listing a product to seller manually - disable when not needed please
       // seller1.inventory.addProduct(new Product("productID","productName",1.1, 1.1,5, "http://www.picture.jpg","description"));
        
        StoreSystem storeSys = new StoreSystem();
        seller1.seri();
        seller2.seri();
        
        //Call to Create StoreSystem UI
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowUI();
            }
        });
    }
}


        /*
         * //change listeners
        ChangeListener listener = new 
                ChangeListener(){
                    public void stateChanged(ChangeEvent event){
                        textArea.setText(cart.format(formatter));
                    }
                };
        cart.addChangeListener(listener);
        
        //button listener
        addButton.addActionListener(new
                ActionListener(){
                   public void actionPerformed(ActionEvent event){
                       LineItem item = (LineItem) combo.getSelectedItem();
                       cart.addItem(item);
                   } 
                });
         */