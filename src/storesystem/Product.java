/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

import java.awt.Font;
import java.awt.Frame;
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
     * Constructs a product
     * @param description the description
     * @param price the price
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
    }
        public Product(){
        this.description = "";
        this.productID="";
        this.productName="";
        this.invoicePrice=0;
        this.sellPrice=0;
        this.quantity=0;
        this.picture="http://i654.photobucket.com/albums/uu265/angflyer/bth_unknown.jpg";
    }
    
    public double getSellPrice(){
        return sellPrice;
    }
     public int getQuantity(){
        return quantity;
    }
      public String getProductID(){
        return productID;
    } public String getProductName(){
        return productName;
    } public double getInvoicePrice(){
        return invoicePrice;}
     public String getDescription(){
        return description;
    } public String getPicture(){
        return picture;
    } public int getSold(){
        return sold;
    }
    public void showProduct(User user){
        
         final User userr=user;
        if (user.type==2){   //THIS IS A SELLER WATCHIN THIS ITEM
                    
                    final Frame frame = new JFrame(this.getProductName());//pp.getPicture());
                    Image image = null;
                    try {
                        URL url = new URL(this.getPicture());
                        image = ImageIO.read(url);
                    } 
                    catch (IOException ee) {
                            ee.printStackTrace();
                    }
                    JLabel label = new JLabel(new ImageIcon(image));
                    frame.add(label);
                    TextField fieldDesc1=new TextField("Product ID");
                    fieldDesc1.setEditable(false);
                    fieldDesc1.setFont(new Font("Serif",Font.BOLD,15));
                    TextField fieldDesc2=new TextField("Product Name");
                    fieldDesc2.setEditable(false);
                    fieldDesc2.setFont(new Font("Serif",Font.BOLD,15));
                    TextField fieldDesc3=new TextField("Invoice Price");
                    fieldDesc3.setEditable(false);
                    fieldDesc3.setFont(new Font("Serif",Font.BOLD,15));
                    TextField fieldDesc4=new TextField("Sell Price");
                    fieldDesc4.setEditable(false);
                    fieldDesc4.setFont(new Font("Serif",Font.BOLD,15));
                    TextField fieldDesc5=new TextField("Quantity");
                    fieldDesc5.setEditable(false);
                    fieldDesc5.setFont(new Font("Serif",Font.BOLD,15));
                    TextField fieldDesc6=new TextField("Description");
                    fieldDesc6.setEditable(false);
                    fieldDesc6.setFont(new Font("Serif",Font.BOLD,15));
                    TextField fieldDesc7=new TextField("Picture URL");
                    fieldDesc7.setEditable(false);
                    fieldDesc7.setFont(new Font("Serif",Font.BOLD,15));
                    
                    final TextField textField5=new TextField(this.getProductID());
                    final TextField textField2=new TextField(this.getProductName());
                    final TextField textField3=new TextField(Double.toString(this.getInvoicePrice()));
                    final TextField textField6=new TextField(Double.toString(this.getSellPrice()));
                    final TextField textField7=new TextField(Integer.toString(this.getQuantity()));
                     final TextField textField1=new TextField(this.getDescription());
                     //textField1.setFont(new Font("Serif",Font.BOLD,35));
                     final TextField textField4=new TextField(this.getPicture());
                     final Product currentProduct=this;
                     JButton saveButton = new JButton("Save Changes");
                     
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
                            JOptionPane.showMessageDialog(frame, "Product has been saved.\nThank you");
                            //frame.dispose();
                            
                            StoreSystem regFace = new StoreSystem(userr.name);
                            regFace.setVisible(true);
                            regFace.main();               
                            frame.dispose();
                            
                     }   
                        }
                    ); 
                     textField1.setEditable(true);
                     textField2.setEditable(true);
                     textField3.setEditable(true);
                     textField4.setEditable(true);
                     JPanel listPane = new JPanel();
                     listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
                     listPane.add(label);
                     
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
                     
                     listPane.add(saveButton);
                     frame.add(listPane);
                    frame.setAlwaysOnTop(true);
                    frame.setSize(370, 800);
                    frame.setLocation(50, 50);
                    frame.setVisible(true);
        }
        else if (user.type==1){   //THIS IS A BUYER WATCHING THIS ITEM
            Frame frame = new JFrame(this.getProductName());//pp.getPicture());
                    Image image = null;
                    try {
                        URL url = new URL(this.getPicture());
                        image = ImageIO.read(url);
                    } 
                    catch (IOException ee) {
                            ee.printStackTrace();
                    }
                    JLabel label = new JLabel(new ImageIcon(image));
                    frame.add(label);
                    System.out.println("Added Picture!!!!");
                     TextField textField1=new TextField(this.getDescription());
                     textField1.setFont(new Font("Serif",Font.BOLD,35));
                     TextField textField2=new TextField(this.getProductName());
                     TextField textField3=new TextField(Double.toString(this.getInvoicePrice()));     
                     textField1.setEditable(false);
                     textField2.setEditable(false);
                     textField3.setEditable(false);
                     JPanel listPane = new JPanel();
                     listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
                     listPane.add(label);
                     listPane.add(textField1);
                     listPane.add(textField2);
                     listPane.add(textField3); 
                     frame.add(listPane);
                    frame.setAlwaysOnTop(true);
                    frame.setSize(300, 500);
                    frame.setLocation(350, 400);
                    frame.setVisible(true);
            
        }
    }
    
    private String productID;
    private String productName;
    private double invoicePrice;
    private double sellPrice;
    private int quantity;
    private String description; //the description of a product 
    private String picture;
    
    private int sold;   
    
}
