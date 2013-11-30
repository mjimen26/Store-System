/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.TextField;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    public void showProduct(int user){
        
        if (user==2){   //THIS IS A SELLER WATCHIN THIS ITEM
                    
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
                     TextField textField4=new TextField(this.getPicture());
                     textField1.setEditable(true);
                     textField2.setEditable(true);
                     textField3.setEditable(true);
                     textField4.setEditable(true);
                     JPanel listPane = new JPanel();
                     listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
                     listPane.add(label);
                     listPane.add(textField1);
                     listPane.add(textField2);
                     listPane.add(textField3); 
                     listPane.add(textField4); 
                     frame.add(listPane);
                    frame.setAlwaysOnTop(true);
                    frame.setSize(300, 500);
                    frame.setLocation(350, 400);
                    frame.setVisible(true);
        }
        else if (user==1){   //THIS IS A BUYER WATCHING THIS ITEM
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
