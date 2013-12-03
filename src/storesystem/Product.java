
package storesystem;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static storesystem.CheckoutSystem.frame;

/**
 * A product with price and description
 */
public class Product implements Serializable{
    
    /**
     * Constructs a Product
     * @param productID the product ID
     * @param productName the product name
     * @param invoicePrice the invoice price
     * @param sellPrice the selling price
     * @param quantity the quantity
     * @param picture the image
     * @param description the description
     */
    public Product(String productID, String productName,
            double invoicePrice, double sellPrice,
            int quantity, String picture,
            String description){
        this.description = description;
        this.productID=productID;
        this.productName=productName;
        this.invoicePrice=invoicePrice;
        this.sellPrice=sellPrice;
        this.quantity=quantity;
        this.picture=picture;
        this.sold=0;
    }
    
    /**
     * Constructs a Product
     */
    public Product(){
        this.description = "";
        this.productID="";
        this.productName="";
        this.invoicePrice=0;
        this.sellPrice=0;
        this.quantity=0;
        this.picture="http://i654.photobucket.com/albums/uu265/angflyer/bth_unknown.jpg";
        this.sold=0;
    }
    
    //Returns sellPrice
    public double getSellPrice(){
        return sellPrice;
    }
    //Returns quantity 
    public int getQuantity(){
        return quantity;
    }
    //Returns productID 
    public String getProductID(){
        return productID;
    } 
    //Returns product name
    public String getProductName(){
        return productName;
    } 
    //Returns invoicePrice
    public double getInvoicePrice(){
        return invoicePrice;
    }
    //Returns desciption
    public String getDescription(){
        return description;
    } 
    //Returns image
    public String getPicture(){
        return picture;
    } 
    //Returns amount sold
    public int getSold(){
        return sold;
    }
    
    /**
     * Keeps track of the amount sold
     * @param x current amount sold
     */
    public void sell(int x){
        sold+=x;
        quantity-=x;
    }
    
    /**
     * Displays a Product Window
     * @param user the user type
     */
    public void showProduct(User user){
        
        final User userr=user;
        
        //If a seller clicks the product name, 
        //Display all fields and make all editable
        if (user.type==2){      
            final Frame frame = new JFrame(this.getProductName());
            frame.setLayout(new BorderLayout());
            frame.setResizable(false);
            Image image = null;
            try {
                URL url = new URL(this.getPicture());
                image = ImageIO.read(url);
            } 
            catch (IOException ee) {
                ee.printStackTrace();
            }
            
            JLabel label = new JLabel(new ImageIcon(image));
            frame.add(label, BorderLayout.NORTH);
                    
            JLabel fieldDesc1 = new JLabel("Product ID");
            JLabel fieldDesc2=new JLabel("Product Name");
            JLabel fieldDesc3=new JLabel("Invoice Price");
            JLabel fieldDesc4=new JLabel("Sell Price");
            JLabel fieldDesc5=new JLabel("Quantity");
            JLabel fieldDesc6=new JLabel("Description");
            JLabel fieldDesc7=new JLabel("Picture URL");
                    
            final TextField textField5=new TextField(this.getProductID());
            final TextField textField2=new TextField(this.getProductName());
            final TextField textField3=new TextField(Double.toString(this.getInvoicePrice()));
            final TextField textField6=new TextField(Double.toString(this.getSellPrice()));
            final TextField textField7=new TextField(Integer.toString(this.getQuantity()));
            final TextField textField1=new TextField(this.getDescription());
            final TextField textField4=new TextField(this.getPicture());
            final Product currentProduct=this;
            JButton saveButton = new JButton("Save Changes");
              
            //Seller updates a Product
            //Seller clicks on Save Changes button
            saveButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){            
                    currentProduct.productID=textField5.getText();
                    currentProduct.productName=textField2.getText();
                    currentProduct.invoicePrice=Double.parseDouble(textField3.getText());
                    currentProduct.sellPrice=Double.parseDouble(textField6.getText());
                    currentProduct.quantity=Integer.parseInt(textField7.getText());
                    currentProduct.description=textField1.getText();
                    currentProduct.picture=textField4.getText();
                    ((Seller)userr).seri();                            
                    JOptionPane.showMessageDialog(frame, 
                            "Product has been saved.\nThank you");
                    
                    //Refresh StoreSystem Window
                    StoreSystem regFace = new StoreSystem(userr.name);
                    regFace.setVisible(true);
                    regFace.main();               
                    frame.dispose();
                }   
            }); 
        
            textField1.setEditable(true);
            textField2.setEditable(true);
            textField3.setEditable(true);
            textField4.setEditable(true);
        
        JPanel listPane = new JPanel(new GridLayout(0,2));             
        listPane.add(fieldDesc1);
        listPane.add(textField5);
                     
        listPane.add(fieldDesc2);
        listPane.add(textField2);
                     
        listPane.add(fieldDesc3);
        listPane.add(textField3); 
                     
        listPane.add(fieldDesc4);
        listPane.add(textField6);
                     
        listPane.add(fieldDesc5);
        listPane.add(textField7);
                     
        listPane.add(fieldDesc6);                     
        listPane.add(textField1);
                     
        listPane.add(fieldDesc7);                     
        listPane.add(textField4);
                     
        frame.add(listPane, BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.SOUTH);
        frame.setAlwaysOnTop(true);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        }//end of if
        
    //If the Customer clicks the prodcut name,
    //Display some fields, not editable
    else if (user.type==1){
        
        Frame frame = new JFrame(this.getProductName());
        frame.setLayout(new BorderLayout());
        //pp.getPicture());
        Image image = null;
        try {
            URL url = new URL(this.getPicture());
            image = ImageIO.read(url);
        } 
        catch (IOException ee) {
            ee.printStackTrace();
        }
        
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label, BorderLayout.NORTH);
        
        //System.out.println("Added Picture!!!!");
        JLabel textField1 = new JLabel(this.getDescription());
        textField1.setFont(new Font("Serif",Font.BOLD,24));
        JLabel textField2 = new JLabel(this.getProductName());
        textField2.setFont(new Font("Serif",Font.BOLD,35));
        JLabel textField3 = 
                new JLabel("Price: $" + Double.toString(this.getSellPrice()));     
        textField3.setFont(new Font("Serif",Font.BOLD,35));
        
        JPanel listPane = new JPanel();
        listPane.setLayout(new GridLayout(0,1));
        listPane.add(textField2);
        listPane.add(textField3); 
        listPane.add(textField1);
        
        frame.add(listPane, BorderLayout.CENTER);
        frame.setAlwaysOnTop(true);
        frame.setSize(400, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        }//end of endif
    }//end of showProduct
    
    private String productID;
    private String productName;
    private double invoicePrice;
    private double sellPrice;
    private int quantity;
    private String description;
    private String picture;
    
    private int sold;   
    
}
