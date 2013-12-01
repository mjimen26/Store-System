/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class CheckoutSystem extends JPanel{
    
    //The Main Panel of Checkout
    private JPanel checkoutPanel = new JPanel();
    public static JFrame frame = new JFrame("Checkout Window");
   ArrayList <Product> toBuyList;//=new ArrayList<Product>(); 
    
    public CheckoutSystem(ArrayList <Product> toBuy){
        toBuyList=toBuy;
        checkoutPanel.setLayout(new BorderLayout());
        
        JPanel textPanel = new JPanel();
        DefaultListModel model = new DefaultListModel();
        for (Product currentProduct:toBuy)
            model.addElement(currentProduct.getProductName()+" $"+currentProduct.getInvoicePrice());

        final JList list = new JList();
        list.setSize(10, 50);
        list.setModel(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

       // final JTextArea text = new JTextArea(10,50);
        //text.setText(toBuyList.get(1).getPicture());
        textPanel.add(new JScrollPane(list));
        
        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        JButton cancelButton = new JButton("Cancel");
        JButton payButton = new JButton("Pay Now");
        buttonPanel.add(cancelButton);
        buttonPanel.add(payButton);
        
        payButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                openDialog(frame);
            }
        });
        
        //This is the top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(0,5));
        JPanel rightPanel = new JPanel();
        
        JLabel prodLbl = new JLabel("Product ID:");
        JTextField prodTxt = new JTextField();
        JLabel qtyLbl = new JLabel("Quantity:");
        JTextField qtyTxt = new JTextField();
        JButton updateBtn = new JButton("Update");
        
        leftPanel.add(prodLbl);
        leftPanel.add(prodTxt);
        leftPanel.add(qtyLbl);
        leftPanel.add(qtyTxt);
        rightPanel.add(updateBtn);
        
        topPanel.add(rightPanel, BorderLayout.EAST);
        topPanel.add(leftPanel, BorderLayout.CENTER);
        //--end of top Panel
        
        checkoutPanel.add(topPanel, BorderLayout.NORTH);
        checkoutPanel.add(textPanel, BorderLayout.CENTER);
        checkoutPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public JPanel getCheckoutPanel(){
        return checkoutPanel;
    }

    private static void openDialog(Frame f){
        final JDialog dialog = new JDialog(f, "Success!", true);
        JPanel panel = new JPanel();  
        JLabel label = new JLabel("Thank you for buying at Z-Bay! GTFO");
        
        panel.add(label);
        
        String input =  JOptionPane.showInputDialog(dialog 
       ,"Enter the Credit Card Number:");
        
        dialog.getContentPane().add(panel);  
        dialog.setSize(250,80);  
        dialog.setLocationRelativeTo(f);  
        dialog.setVisible(true);  
    }
    
    public void main(ArrayList <Product> toBuy){
        
        toBuyList=toBuy;
        frame.getContentPane().add(new CheckoutSystem(toBuy).getCheckoutPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
