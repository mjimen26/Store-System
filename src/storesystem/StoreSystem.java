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

/**
 * StoreSystem is in charge of handling the main methods of the program.
 * It's frame is divided into two Panels, the top being the Inventory 
 *      and the bottom being the Shopping Cart.
 */
public class StoreSystem extends JPanel{
    
    final ArrayList <Product> pProduct = new ArrayList<Product>();
    final ArrayList <JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
    
    static JFrame frame = new JFrame("Welcome back " ); 
    JPanel centerPanel; //Inventory 
    JPanel southPanel;  //Shopping Cart
    private JPanel mainPanel = new JPanel();
    
    /**
     * Constructs a new StoreSystem.
     * @param who the type of user using the program
     */
    public StoreSystem(String who){

        which=who;
        if (customer.name.equals(which)) current=customer;
        if (seller1.name.equals(which)) current=seller1;
        if (seller2.name.equals(which)) current=seller2;
        
        //Create the two Panels
        centerPanel = createCenterPanel();
        southPanel = createSouthPanel();
        
        //Add the two Panels to the mainPanel
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(centerPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);        
    }
    
    /*
     * Creates the Center Panel displays the Inventory.
     * It is also responsible for running the listeners of both customer and
     * the seller.
     * @return the centerPanel
     */
    private JPanel createCenterPanel(){
        JPanel centerPanel = new JPanel(new GridLayout(0,4));
        DecimalFormat df = new DecimalFormat("#.##");
                    
        ArrayList pName = new ArrayList();
        ArrayList pPrice = new ArrayList();
        ArrayList pQuantity = new ArrayList();
        
        //Distinguishes the type of user that is logged in.
        //If customer (type=1), show all products
        //Else if seller (type=2), show private inventory only
        if (current.type==1){
            System.out.println("this is logged in: "+current.name);
            for (Product p:seller1.inventory.inventory){
                if (p.getQuantity()>0){
                    pProduct.add(p);
                    checkBoxes.add(new JCheckBox());
                    System.out.println(p.getProductName());
                }
            }
            for (Product p:seller2.inventory.inventory){
                if (p.getQuantity()>0){
                    pProduct.add(p);
                    checkBoxes.add(new JCheckBox());
                    System.out.println(p.getProductName());
                }
            }
        }
        else if (current.name.equals("one")){
            System.out.println("this is logged in: "+current.name);
            for (Product p:seller1.inventory.inventory){
                {
                    pProduct.add(p);
                    checkBoxes.add(new JCheckBox());
                    System.out.println(p.getProductName());
                }
            }
        }
        else if (current.name.equals("two")){
            System.out.println("this is logged in: "+current.name);
            for (Product p:seller2.inventory.inventory){
                {
                    pProduct.add(p);
                    checkBoxes.add(new JCheckBox());
                    System.out.println(p.getProductName());
                }
            }
        }

        //JComponents necessary to display in the Inventory.
        JPanel checkPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel namePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel spricePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel qtyPanel = new JPanel(new GridLayout(0, 1, 5, 5));      
        
        //Display the products
        int i=-1;
        final User currentUser=current;
        for (Product pp:pProduct){
            i++;
            final Product curent=pp;
            JPanel check = new JPanel(new BorderLayout());
            JPanel name = new JPanel(new BorderLayout());
            JPanel sell = new JPanel(new BorderLayout());
            JPanel qty = new JPanel(new BorderLayout());
            
            //When a Product name is clicked, display the Product Window
            //showing all the details of that Product
            name.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    curent.showProduct(currentUser);
                    if (current.type==2)  { 
                        frame.dispose();
                        mainPanel.resize(10, 10);
                    }
                }
            });
            
            //Add the fields on their corresponding Panel
            check.add(checkBoxes.get(i));
            name.add(new JLabel(pp.getProductName()), BorderLayout.NORTH);
            sell.add(new JLabel("$" + pp.getSellPrice()), BorderLayout.NORTH);
            qty.add(new JLabel("Qty: " + pp.getQuantity()), BorderLayout.NORTH);
            
            checkPanel.add(check);
            namePanel.add(name);
            spricePanel.add(sell);
            qtyPanel.add(qty);
        }
        
        if ((current.type==1))
            centerPanel.add(checkPanel);
            centerPanel.add(namePanel);
            centerPanel.add(spricePanel);
            centerPanel.add(qtyPanel);
        
        return centerPanel;
    }//end of createCenterPanel
    
    /*
     * The South Panel displays the Shopping Cart and is updated 
     *      every time the Update Cart button is clicked.
     * The arrangement and layout of components change depending on the 
     *      type of user logged in.
     * @return the southPanel
     */
    private JPanel createSouthPanel(){
        final JPanel southPanel = new JPanel(new BorderLayout());
        final JTextArea textArea = new JTextArea(10,50);
        
        //Customer
        JButton addButton = new JButton("Update Cart");
        JButton checkoutButton = new JButton("Checkout");
        //Seller
        JButton viewTransactions = new JButton("View Transactions");
        JButton addNewProduct = new JButton("Add New Product");
        
        //Adding a Product to the Shopping Cart 
        //Customer clicks Update Cart button
        addButton.addMouseListener(new MouseAdapter()  {
            @Override
            public void mouseClicked(MouseEvent e) {
                String toSet="";
                double total=0;
                int i=-1;
                for (Product pp:pProduct){     
                    i++;
                    if (checkBoxes.get(i).isSelected()){
                        toSet+="Name: "+pp.getProductName()+
                            "\tPrice: "+pp.getSellPrice()+
                            "\tDescription: "+pp.getDescription()+"\n";
                        total+=pp.getSellPrice();
                    }
                }
                
                DecimalFormat df = new DecimalFormat("#.##");
                toSet+="\nTOTAL:  $"+df.format(total);
                textArea.setText(toSet);
                textArea.setEditable(false);
                southPanel.repaint();
            }
        }); 
        
        //Proceed to Checkout
        //Customer clicks Checkout button
        checkoutButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                ArrayList <Product> toBuy = new ArrayList<Product>();
                int i=-1;
                for (Product pp:pProduct){
                    i++;
                    if (checkBoxes.get(i).isSelected())
                        toBuy.add(pp);
                }
                
                CheckoutSystem check = 
                        new CheckoutSystem(toBuy,seller1,seller2,which);
                check.setVisible(true);
                check.main(toBuy,seller1,seller2,which);
                frame.dispose();
            }
        });
        
        //Review transaction details
        //Seller clicks View Transactions button
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
                
                DecimalFormat df = new DecimalFormat("#.##");
                toSet+="\nREVENUES: " + revenues +
                        "     COSTS: " + costs +
                        "     PROFIT: " + df.format(revenues-costs);
                textArea.setText(toSet);
                textArea.setEditable(false);
                southPanel.repaint();
            }
        }); 
        
        //Seller adds new product
        //Seller clicks Add New Product button
        addNewProduct.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                Product newProduct=new Product();
                ((Seller)current).inventory.addProduct(newProduct);
                newProduct.showProduct(current);
                frame.dispose();
                mainPanel.resize(10, 10);
            }
        });
    
        //JComponents for Shopping Cart
        JPanel ctrlPanel = new JPanel(new GridLayout(0,2));
        if (current.type==1) ctrlPanel.add(addButton);
        else ctrlPanel.add(viewTransactions);
        //show Checkout only for customer
        if (current.type==1) ctrlPanel.add(checkoutButton);
        //show Add New Product only for seller
        else ctrlPanel.add(addNewProduct);     
        
        //Add all Panels to the Shopping Cart Panel
        southPanel.add(new JScrollPane(textArea), BorderLayout.NORTH);
        southPanel.add(ctrlPanel, BorderLayout.SOUTH);
            
        return southPanel;
    }//end of createSouthPanel
    
    /**
     * Returns the Main Panel containing the Inventory and Shopping Cart Panels
     * @return mainPanel
     */
    public JPanel getMainPanel()
    {
        return mainPanel;
    }
 
    /**
     * Creates then shows the UI
     */
    private static void createAndShowUI()
    {
        //final JFrame frame = new JFrame("Welcome back "+which );
        frame.setName("Welcome back "+which );
        frame.getContentPane().add(new StoreSystem(which).getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        
    }
    
    public User customer=new Customer("Marvin","icecream");
    public Seller seller1=new Seller("one","password");
    public Seller seller2=new Seller("two","password");
    static public String which;
    User current;
    
    public  void main() {    
        //listing a product to seller manually - disable when not needed please
        if (seller1.inventory.inventory.isEmpty()){
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

            //do serialization when (1)add\edit product, (2) Product is sold
            seller1.seri();
            seller2.seri();
        }
       
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