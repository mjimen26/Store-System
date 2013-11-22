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
    
    public StoreSystem(){}
    
    //Constructor for StoreSystem
    public StoreSystem(String who){
      
          which=who;
        if (customer.name.equals(which)) current=customer;
        if (seller1.name.equals(which)) current=seller1;
        if (seller2.name.equals(which)) current=seller2;
      
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
                    
        ArrayList pName = new ArrayList();
        ArrayList pPrice = new ArrayList();
        ArrayList pQuantity = new ArrayList();
        final ArrayList <Product> pProduct = new ArrayList<Product>();
        
       
  
        if (current.type==1){
            //Iterator <Product> itr1=Iterators.forArray(seller1.inventory.inventory);//seller1.inventory.inventory.iterator();
 System.out.println("this is logged in: "+current.name);
            for (Product p:seller1.inventory.inventory){
                if (p.getQuantity()>0){
                    pProduct.add(p);
                   // pName.add(p.getProductName());
                   // pPrice.add(p.getSellPrice());
                   // pQuantity.add(p.getQuantity());
                    System.out.println(p.getProductName());
                }
            }
            for (Product p:seller2.inventory.inventory){
                if (p.getQuantity()>0){
                    pProduct.add(p);
                    //pName.add(p.getProductName());
                    //pPrice.add(p.getSellPrice());
                    //pQuantity.add(p.getQuantity());
                    System.out.println(p.getProductName());
                }
            }
        }
        else if (current.name.equals("one")){
            //Iterator <Product> itr1=Iterators.forArray(seller1.inventory.inventory);//seller1.inventory.inventory.iterator();
 System.out.println("this is logged in: "+current.name);
            for (Product p:seller1.inventory.inventory){
                if (p.getQuantity()>0){
                    pProduct.add(p);
                    //pName.add(p.getProductName());
                    //pPrice.add(p.getSellPrice());
                    //pQuantity.add(p.getQuantity());
                    System.out.println(p.getProductName());
                }
            }
        }
        else if (current.name.equals("two")){
            //Iterator <Product> itr1=Iterators.forArray(seller1.inventory.inventory);//seller1.inventory.inventory.iterator();
 System.out.println("this is logged in: "+current.name);
            for (Product p:seller2.inventory.inventory){
                if (p.getQuantity()>0){
                    pProduct.add(p);
                    //pName.add(p.getProductName());
                    //pPrice.add(p.getSellPrice());
                    //pQuantity.add(p.getQuantity());
                    System.out.println(p.getProductName());
                }
            }
        }
//        String[] productName=new String [pProduct.size()];
//        double[] sellPrice=new double [pProduct.size()];
//        int[] quantity=new int [pProduct.size()];
//        for (int i=0;i<pProduct.size();i++){
//            productName[i]=(pProduct.get(i).getProductName());
//            sellPrice[i]=pProduct.get(i).getSellPrice();
//            quantity[i]=pProduct.get(i).getQuantity();
//        }

        
        JPanel checkPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel namePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel spricePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel qtyPanel = new JPanel(new GridLayout(0, 1, 5, 5));
      
        //display the products
        //for(int x=0; x<pProduct.size(); x++){
        //final Product p;
          for (Product pp:pProduct){  
            JPanel check = new JPanel(new BorderLayout());
            JPanel name = new JPanel(new BorderLayout());
             //final Product p=pp;
            
              name.addMouseListener(new MouseAdapter()  {
                  
                  @Override
                public void mouseClicked(MouseEvent e) {
                    // you can open a new frame here as
                    // i have assumed you have declared "frame" as instance variable
                    Frame frame = new JFrame("TITLE HERE ");//pp.getPicture());
                    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setAlwaysOnTop(true);
                    frame.setSize(300, 500);
                    frame.setLocation(350, 400);
                    frame.setVisible(true);
                    }
                }); 
            JPanel sell = new JPanel(new BorderLayout());
            JPanel qty = new JPanel(new BorderLayout());
            
            check.add(new JCheckBox());
            name.add(new JLabel(pp.getProductName()), BorderLayout.NORTH);
            sell.add(new JLabel("$" + pp.getSellPrice()), BorderLayout.NORTH);
            qty.add(new JLabel("Qty: " + pp.getQuantity()), BorderLayout.NORTH);
            
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
        //ADD BUTTON LISTENER
        addButton.addMouseListener(new MouseAdapter()  {
                  
                  @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("ADD BUTTON PRESSED");
                    }
                }); 
    
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
        JFrame frame = new JFrame("Welcome back "+which );
        frame.getContentPane().add(new StoreSystem(which).getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public User customer=new Customer("Marvin","icecream");
    public Seller seller1=new Seller("one","password");
    public Seller seller2=new Seller("two","password");
    static public String which;
    User current;
    
    public  void main() {
        
 
        
        
       //listing a product to seller manually - disable when not needed please
       // seller1.inventory.addProduct(new Product("productID1","productName1",1.1, 1.1,5, "http://vk.com/images/gifts/256/454.jpg","description of product 1"));
       // seller1.inventory.addProduct(new Product("productID2","productName2",2.2, 1.1,10, "http://vk.com/images/gifts/256/455.jpg","A bear"));
       // seller1.inventory.addProduct(new Product("productID3","productName3" ,3.3, 1.1,15, "http://vk.com/images/gifts/256/462.jpg","Plastic soldier"));
        
               // do serialization only when : add\edit product AND when sold product
        // seller1.seri();
       // seller2.seri();
        
        StoreSystem storeSys = new StoreSystem(which);

        
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