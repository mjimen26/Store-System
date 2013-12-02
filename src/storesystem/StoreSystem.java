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
        JButton viewTransactions = new JButton("View Transactions");
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
                            toSet+="Name: "+pp.getProductName()+
                                    "\tPrice: "+pp.getInvoicePrice()+
                                    "\tDescription: "+pp.getDescription()+"\n";
                        }
                    textArea.setText(toSet);
                    southPanel.repaint();
            }
        }); 
        //View Transactions Listener
        viewTransactions.addMouseListener(new MouseAdapter()  {
            @Override
            public void mouseClicked(MouseEvent e) {
                double revenues=0,costs=0;
                String toSet="";
                    
                    for (Product pp:pProduct){
                        revenues+=pp.getSellPrice()*pp.getSold();
                        costs+=pp.getInvoicePrice()*(pp.getSold()+pp.getQuantity());
                        toSet+="Item: "+pp.getProductName()+
                                "     Quantity sold: "+pp.getSold()+
                                "     Quantity availble "+pp.getQuantity()+
                                "     Invoice price: "+pp.getInvoicePrice()+
                                "     Sell price:  "+pp.getSellPrice()+"\n";
                        }
                    toSet+="\nREVENUES: "+revenues+"     COSTS: "+costs+"     PROFIT: "+(revenues-costs);
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
        else ctrlPanel.add(viewTransactions);
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
            //seller1.inventory.addProduct(new Product("productID1","productName1",1.1, 1.1,5, "http://vk.com/images/gifts/256/454.jpg","description of product 1"));
            seller1.inventory.addProduct(new Product("11","Bicycle",1990.50,    2149.99,    1, "http://static.bootic.com/_pictures/1394318/400x400/trek-top-fuel-9-9-ssl.jpg", "Trek Top Fuel 9.9 SSL 2011"));
            seller1.inventory.addProduct(new Product("12","Snorkel" ,12.00,     15.55,      10, "http://feeds2.yourstorewizards.com/1749/images/400x400/us-divers-cozumel-diving-mask-seabreeze-snorkel-and-proflex-fins-snorkeling-set.jpg","A snorkel set"));
            seller1.inventory.addProduct(new Product("13","Monster" ,6.99,      13.13,      12, "http://www.meijer.com/assets/product_images/styles/xlarge/1000896_801471BS_A_400.jpg","Kids Monster INC. Costume"));
            seller1.inventory.addProduct(new Product("14","Lighter" ,1.5,       2.99,       75, "http://www.freshpromotions.com.au/products/promotionallighter11.jpg","Plastic lighter, random color"));
            seller1.inventory.addProduct(new Product("15","Journal" ,3.0,       3.33,       20, "http://images6.fanpop.com/image/photos/34300000/barbie-magazine-barbie-movies-34389436-400-400.jpg","Barbie Magazine, latest edition"));
            seller1.inventory.addProduct(new Product("16","Bobcats" ,700,       949.99,     1, "http://photos.bwca.com/u/UNAS10-070112-045939.JPG","Used, in good condition"));
            
            seller2.inventory.addProduct(new Product("21","Oranges" ,7.50,      9.99,       20, "http://images.honeybell.com/is/image/honeybell/feature2010/1320_NR10-christmas-red-navels-large-sampler.jpg","Red Oranges, 3Lb."));
            seller2.inventory.addProduct(new Product("22","Binders" ,1.5,       3.0,        15, "http://us.123rf.com/400wm/400/400/franckito/franckito0809/franckito080900081/3619034-3d-rendering-of-a-group-of-ring-binders-in-different-colors.jpg","1200 pages binder, random color"));
            seller2.inventory.addProduct(new Product("23","Speaker" ,55.03,     97.5,        25, "http://www.seismicaudiospeakers.com/v/vspfiles/photos/SA-12T-2.jpg","12 Pro Audio: DJ Speaker"));
            seller2.inventory.addProduct(new Product("24","Dusters" ,7.0,       15.50,      20, "http://img2.wfrcdn.com/lf/49/hash/3907/3507081/1/Ultrajet®+Dusters+-+8+oz.+ultrajet+all-way+duster+aerosol.jpg","Chemtronics Ultrajet® Dusters-8oz."));
            seller2.inventory.addProduct(new Product("25","Needles" ,1.15,      2.15,       35, "http://img2.timeinc.net/health/images/gallery/condition-centers/needle-syringe-400x400.jpg","Needle and syringe: 10cc capacity"));
            seller2.inventory.addProduct(new Product("26","Bubbles" ,0.99,      1.79,       50, "http://ecx.images-amazon.com/images/I/51TKrGtToiL.jpg","Miracle Bubbles Party Bubbles, 6-PackRead"));
            

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