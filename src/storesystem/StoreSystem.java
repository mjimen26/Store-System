package storesystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * StoreSystem is in charge of hangling the main methods
 * in the entire application
 */
public class StoreSystem extends JPanel{
    
    final ArrayList <Product> pProduct = new ArrayList<Product>();
    final ArrayList <JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
    //New Shopping Cart and the Interface
    final ShoppingCart cart = new ShoppingCart();
    final ShoppingCartFormatter formatter = new SimpleFormatter();
    static JFrame frame = new JFrame("Welcome back " ); 
        
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
    
    /*
     * The Center Panel displays the inventory
     */
    private JPanel createCenterPanel(){
        //North Panel
        JPanel centerPanel = new JPanel(new GridLayout(0,4));
        DecimalFormat df = new DecimalFormat("#.##");
                    
        ArrayList pName = new ArrayList();
        ArrayList pPrice = new ArrayList();
        ArrayList pQuantity = new ArrayList();
        //final ArrayList <Product> pProduct = new ArrayList<Product>();
        
        //Distinguishes the type of user that is logged in.
        //If customer, show all products
        //Else if seller, show private inventory only
        if (current.type==1){
            //Iterator <Product> itr1=Iterators.forArray(seller1.inventory.inventory);//seller1.inventory.inventory.iterator();
            System.out.println("this is logged in: "+current.name);
            for (Product p:seller1.inventory.inventory){
                if (p.getQuantity()>0){
                    pProduct.add(p);
                    checkBoxes.add(new JCheckBox());
                   // pName.add(p.getProductName());
                   // pPrice.add(p.getSellPrice());
                   // pQuantity.add(p.getQuantity());
                    System.out.println(p.getProductName());
                }
            }
            for (Product p:seller2.inventory.inventory){
                if (p.getQuantity()>0){
                    pProduct.add(p);
                    checkBoxes.add(new JCheckBox());
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
                {
                    pProduct.add(p);
                    checkBoxes.add(new JCheckBox());
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
                {
                    pProduct.add(p);
                    checkBoxes.add(new JCheckBox());
                    //pName.add(p.getProductName());
                    //pPrice.add(p.getSellPrice());
                    //pQuantity.add(p.getQuantity());
                    System.out.println(p.getProductName());
                }
            }
        }

        //JComponents necessary to arrange the Inventory neatly.
        JPanel checkPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel namePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel spricePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel qtyPanel = new JPanel(new GridLayout(0, 1, 5, 5));
      
        //display the products
        //for(int x=0; x<pProduct.size(); x++){
        //final Product p;
        int i=-1;
        final User currentUser=current;
          for (Product pp:pProduct){  
              i++;
              final Product curent=pp;
              //SAVE WHICH KIND OF USER AS FINAL
            JPanel check = new JPanel(new BorderLayout());
            
            JPanel name = new JPanel(new BorderLayout());
             //final Product p=pp;
            
              name.addMouseListener(new MouseAdapter()  {
                  
                  @Override
                public void mouseClicked(MouseEvent e) {
                      //final Product currentProduct=curent;
                      curent.showProduct(currentUser);
                      //currentProduct.showProduct(currentUser);
                      if (current.type==2)   
                          frame.dispose();
                      
                    }
                }); 
            JPanel sell = new JPanel(new BorderLayout());
            JPanel qty = new JPanel(new BorderLayout());
            
            check.add(checkBoxes.get(i));
            name.add(new JLabel(pp.getProductName()), BorderLayout.NORTH);
            sell.add(new JLabel("$" + pp.getSellPrice()), BorderLayout.NORTH);
            qty.add(new JLabel("Qty: " + pp.getQuantity()), BorderLayout.NORTH);
            
            checkPanel.add(check);
            namePanel.add(name);
            spricePanel.add(sell);
            qtyPanel.add(qty);
        }
        
        //add the panels to centerPanel
        centerPanel.add(checkPanel);
        centerPanel.add(namePanel);
        centerPanel.add(spricePanel);
        centerPanel.add(qtyPanel);
        
        
        return centerPanel;
    }//end of createCenterPanel
    
    /*
     * The South Panel displays the Shopping Cart and is updated 
     * every time the Update Cart button is clicked.
     */
    private JPanel createSouthPanel(){
        //South Panel
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea(10,50);
        JButton addButton = new JButton("Update Cart");
        JButton checkoutButton = new JButton("Checkout");
        JButton addNewProduct = new JButton("Add New Product");
        
        //Update Listener
        addButton.addMouseListener(new MouseAdapter()  {
            @Override
            public void mouseClicked(MouseEvent e) {
                String toSet="";
                    int i=-1;
                    for (Product pp:pProduct){
                        i++;
                        if (checkBoxes.get(i).isSelected())
                            toSet+=pp.getProductName()+" "
                                    +pp.getInvoicePrice()+" "
                                    +pp.getDescription()+"\n";
                        }
                    textArea.setText(toSet);
                    southPanel.repaint();
            }
        }); 
        
        //Checkout Listener
        checkoutButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                ArrayList <Product> toBuy = new ArrayList<Product>();
                int i=-1;
                for (Product pp:pProduct){
                        i++;
                        if (checkBoxes.get(i).isSelected())
                            toBuy.add(pp);
                  }
                CheckoutSystem check = new CheckoutSystem(toBuy,seller1,seller2,which);
                check.setVisible(true);
                check.main(toBuy,seller1,seller2,which);
                frame.dispose();
            }
        });
        
          addNewProduct.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                Product newProduct=new Product();
                ((Seller)current).inventory.addProduct(newProduct);
                newProduct.showProduct(current);

                frame.dispose();
                
                //frame.repaint();
                
            }
        });
    
        //JComponents for Shopping Cart
        JPanel ctrlPanel = new JPanel(new GridLayout(0,2));
        if (current.type==1) ctrlPanel.add(addButton);
        if (current.type==1) ctrlPanel.add(checkoutButton);     //show check out only for buyer
        else ctrlPanel.add(addNewProduct);     //show add new only for seller
        
        //the entire South Panel
        southPanel.add(new JScrollPane(textArea), BorderLayout.NORTH);
        southPanel.add(ctrlPanel, BorderLayout.SOUTH);
            
        return southPanel;
    }//end of createSouthPanel
    
    /*
     * Returns the Main Panel containing the Inventory and Shopping Cart Panels
     */
    public JPanel getMainPanel()
    {
        return mainPanel;
    }
 
    private static void createAndShowUI()
    {
        //final JFrame frame = new JFrame("Welcome back "+which );
        frame.setName("Welcome back "+which );
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
        if (seller1.inventory.inventory.isEmpty()){
            seller1.inventory.addProduct(new Product("productID1","productName1",1.1, 1.1,5, "http://vk.com/images/gifts/256/454.jpg","description of product 1"));
            seller1.inventory.addProduct(new Product("productID2","productName2",2.2, 1.1,10, "http://vk.com/images/gifts/256/455.jpg","A bear"));
            seller1.inventory.addProduct(new Product("productID3","productName3" ,3.3, 1.1,15, "http://vk.com/images/gifts/256/462.jpg","Plastic soldier"));

           // do serialization only when : add\edit product AND when sold product
            seller1.seri();
            seller2.seri();
        }
        ////////////////////////????????????????//////////////////////
        //StoreSystem storeSys = new StoreSystem(which);

        //Call to Create StoreSystem UI
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowUI();
            }
        });
    }//end of main
   
}//end of StoreSystem class

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