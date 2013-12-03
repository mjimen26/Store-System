
package storesystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * CheckoutSystem is responsible for Checkout Processes including
 *      displaying the Shopping Cart of the user, updating the quantities
 *      of the Products in the cart, and validating the payment.
 */
public class CheckoutSystem extends JPanel{
    JTextField prodTxt = new JTextField();    
    JTextField qtyTxt = new JTextField();
    JLabel ttlTxtt = new JLabel();  //displays total price of the Producrs 
    final JList list = new JList();    
    
    //The Main Panel of Checkout
    private  JPanel checkoutPanel = new JPanel();
    public static JFrame frame = new JFrame("Checkout Window");
    static ArrayList <Product> toBuyList;
    static Seller seller1,seller2;
    static String which;
    static final ArrayList <Integer> quantities=new ArrayList <Integer>();
    
    /**
     * Constructs a new CheckoutSystem
     * @param toBuy the list of products in Shopping Cart
     * @param seller_1 the 1st seller 
     * @param seller_2 the 2nd seller
     * @param who the user type
     */
    public CheckoutSystem(ArrayList <Product> toBuy,Seller seller_1,
            Seller seller_2,String who){
        
        frame.setResizable(false);
        toBuyList=toBuy;
        seller1=seller_1;
        seller2=seller_2;
        checkoutPanel.setLayout(new BorderLayout());
        which=who;
        
        //Create the Panel which displays the content of the Shopping Cart
        //and the total cost of all the Products in it
        JPanel textPanel = new JPanel();
        DefaultListModel model = new DefaultListModel();
       
        //Display the contents of the Shopping Cart
        for (Product currentProduct:toBuy){
            model.addElement(currentProduct.getProductName()+
                    "          $"+currentProduct.getSellPrice()+
                    "       Available: "+currentProduct.getQuantity());
            quantities.add(1);
        }       
        
        list.setModel(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int index=list.getSelectedIndex();
                        String thisOne =(toBuyList.get(index)).getProductName();
                        System.out.println (thisOne);
                        prodTxt.setText(thisOne);
                        qtyTxt.setText(Integer.toString(quantities.get(index)));    
                    }
                }
            });

        textPanel.add(new JScrollPane(list));
        textPanel.add(ttlTxtt);
        
        int i=-1;
        double total=0;
        for (Product currentProduct:toBuyList){
            i++;
            total+=currentProduct.getSellPrice()*quantities.get(i);
        }
        
        DecimalFormat df = new DecimalFormat("#.##");
        ttlTxtt.setText("TOTAL:  $"+df.format(total));
                    
        //Create the Panel which displays the Cancel and Pay buttons.
        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        JButton cancelButton = new JButton("Cancel");
        JButton payButton = new JButton("Pay Now");
        buttonPanel.add(cancelButton);
        buttonPanel.add(payButton);
        
        //Cancelling Checkout (Go back to shopping)
        //Customer clicks on Cancel button
        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                checkoutPanel.resize(1 ,1);
                StoreSystem regFace= new StoreSystem(which);
                regFace.setVisible(true);
                regFace.main();
            }
        });
        
        //Proceed to Payment
        //Customer clicks on Pay button
        payButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //frame.dispose();
                openDialog(frame);
            }
        });
        
        //Create the Panel which displays the Product Name and Update fields
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(0,5));
        JPanel rightPanel = new JPanel();
        
        JLabel prodLbl = new JLabel("Product ID:");
        JLabel qtyLbl = new JLabel("Quantity:");
        prodTxt.setEditable(false);
        
        //Updating the quantity of a specific Product
        //Customer has selected an item and entered their desired quantity
        //Customer clicks on Update button
        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int index=list.getSelectedIndex();                             
                //If the quantity is less than 0
                if (Integer.parseInt(qtyTxt.getText())<0){
                    JOptionPane.showMessageDialog(null,
                    "Error: Please enter a non-negative number\n", 
                    "Error Massage", JOptionPane.ERROR_MESSAGE);
                }
                //If the desired quantity exceeds the available quantity
                else if (Integer.parseInt(qtyTxt.getText())>
                    toBuyList.get(index).getQuantity()){
                        JOptionPane.showMessageDialog(null,
                        "Error: Not enough available product, please buy up "
                        + "to the available quantity!", "Error Massage",
                        JOptionPane.ERROR_MESSAGE);
                    qtyTxt.setText(Integer.toString(quantities.get(index)));
                    }
                //If quantity is valid
                else{
                    quantities.set(index, Integer.parseInt(qtyTxt.getText()));
                    int i=-1;
                    double total=0;
                    for (Product currentProduct:toBuyList){
                        i++;
                        total+=currentProduct.getSellPrice()*quantities.get(i);
                    }
                    DecimalFormat df = new DecimalFormat("#.##");
                    ttlTxtt.setText("TOTAL:  $"+df.format(total));
                }
            }
        });//end of update listener
        
        leftPanel.add(prodLbl);
        leftPanel.add(prodTxt);
        leftPanel.add(qtyLbl);
        leftPanel.add(qtyTxt);
        rightPanel.add(updateBtn);
        
        topPanel.add(rightPanel, BorderLayout.EAST);
        topPanel.add(leftPanel, BorderLayout.CENTER);
        
        //Add all Panels to the main Panel
        checkoutPanel.add(topPanel, BorderLayout.NORTH);
        checkoutPanel.add(textPanel, BorderLayout.CENTER);
        checkoutPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * gets the main Panel to be displayed in the Checkout Window
     * @return chechoutPanel
     */
    public JPanel getCheckoutPanel(){
        return checkoutPanel;
    }

    /**
     * Open a Dialog Box for payment and confirming the transaction made.
     * @param f the frame
     */
    private static void openDialog(Frame f){
        final JDialog dialog = new JDialog(f, "Success!", true);
        JPanel panel = new JPanel();  
        
        JLabel label = new JLabel("Thank you for buying at Z-Bay!");
        label.setFont(new Font(label.getName(), Font.PLAIN, 
                (int)label.getFont().getSize()+10));
        Icon icon = null;
        try {
            URL url = new URL("http://i109.photobucket.com/albums/n50/towardsthelight/Thanks/MonkeyThanks.gif");
            icon = new ImageIcon(url);
        } 
        catch (IOException ee) {
            ee.printStackTrace();
        }
        
        JLabel label2 = new JLabel(icon);
        panel.add(label2);
        panel.add(label);
        
        String input =  JOptionPane.showInputDialog(dialog,
                "Enter the Credit Card Number:");
        //Serialize
        if (input!=null){
            int i=-1;
            for (Product currentProduct: toBuyList){
                i++; 
                currentProduct.sell(quantities.get(i));
            }
            seller1.seri();
            seller2.seri();
            frame.dispose();
            dialog.getContentPane().add(panel);  
            dialog.setSize(450,350);  
            dialog.setLocationRelativeTo(f);  
            dialog.setVisible(true);
        }
        else {
            frame.dispose();
        }
    }
    
    /**
     * Displays the Checkout Window
     * @param toBuy the Products in the Shopping Cart
     * @param seller_1 the 1st seller
     * @param seller_2 the 2nd seller
     * @param who the user type
     */
    public void main(ArrayList <Product> toBuy,Seller seller_1,
            Seller seller_2,String who){
        
        toBuyList=toBuy;
        seller1=seller_1;
        seller2=seller_2;
        which=who;
        
        frame.getContentPane().add(new CheckoutSystem(toBuy,seller1,
                seller2,which).getCheckoutPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
