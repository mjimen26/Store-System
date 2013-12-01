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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Administrator
 */
public class CheckoutSystem extends JPanel{
    JTextField prodTxt = new JTextField();    
    JTextField qtyTxt = new JTextField();
    final JList list = new JList();    
    
    //The Main Panel of Checkout
    private JPanel checkoutPanel = new JPanel();
    public static JFrame frame = new JFrame("Checkout Window");
   ArrayList <Product> toBuyList;//=new ArrayList<Product>(); 
    
    public CheckoutSystem(ArrayList <Product> toBuy){
        toBuyList=toBuy;
        checkoutPanel.setLayout(new BorderLayout());
        
        JPanel textPanel = new JPanel();
        DefaultListModel model = new DefaultListModel();
       final ArrayList <Integer> quantities=new ArrayList <Integer>();
        for (Product currentProduct:toBuy){
            model.addElement(currentProduct.getProductName()+"          $"+currentProduct.getInvoicePrice()+"       Available: "+currentProduct.getQuantity());
            quantities.add(1);
        }

//        qtyTxt.addActionListener(new java.awt.event.ActionListener() {
//            @Override
//    public void actionPerformed(java.awt.event.ActionEvent e) {
//                
//                int index=list.getSelectedIndex();                             
//                System.out.println("The index is::::::::::::"+index);
//                if (Integer.parseInt(qtyTxt.getText())<=0){
//                                   JOptionPane.showMessageDialog(null,
//                                           "Error: Please enter number bigger than 0", "Error Massage",
//                                           JOptionPane.ERROR_MESSAGE);
//                               }  
//                if (Integer.parseInt(qtyTxt.getText())>quantities.get(index)){
//                                   JOptionPane.showMessageDialog(null,
//                                           "Error: Not enough available product, please buy up to the offered quantity!", "Error Massage",
//                                           JOptionPane.ERROR_MESSAGE);
//                               }
//     }
//});
       
        
        list.setModel(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
//                            DocumentWrapper docWrapper = (DocumentWrapper) list
//                                    .getSelectedValue();
//                            if (docWrapper != null) {
//                                output.setText(docWrapper.getData());
//                            } else {
//                                output.setText("");
//                            }
                            int index=list.getSelectedIndex();
                            String thisOne =(toBuyList.get(index)).getProductName();
                            System.out.println (thisOne);
                            prodTxt.setText(thisOne);
                            qtyTxt.setText(Integer.toString(quantities.get(index)));
                            //quantities.set(list.getSelectedIndex(), WIDTH)
                            
                        }
                    }
                });

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
        JLabel qtyLbl = new JLabel("Quantity:");
        prodTxt.setEditable(false);
        
        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
                
                int index=list.getSelectedIndex();                             
                System.out.println("The index is::::::::::::"+index+" available: "+toBuyList.get(index).getQuantity());
                if (Integer.parseInt(qtyTxt.getText())<0){
                                   JOptionPane.showMessageDialog(null,
                                           "Error: Please enter a non-negative number\n", "Error Massage",
                                           JOptionPane.ERROR_MESSAGE);
                               }  
                else if (Integer.parseInt(qtyTxt.getText())>toBuyList.get(index).getQuantity()){
                                   JOptionPane.showMessageDialog(null,
                                           "Error: Not enough available product, please buy up to the offered quantity!", "Error Massage",
                                           JOptionPane.ERROR_MESSAGE);
                               }
                else{
                    quantities.set(index, Integer.parseInt(qtyTxt.getText()));
                    
                }
     }
});
        
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
