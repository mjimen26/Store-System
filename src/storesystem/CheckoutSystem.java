/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Administrator
 */
public class CheckoutSystem extends JPanel{
    JTextField prodTxt = new JTextField();    
    JTextField qtyTxt = new JTextField();
    //JTextField ttlTxt = new JTextField();
    JLabel ttlTxtt = new JLabel();
    final JList list = new JList();    
    
    //The Main Panel of Checkout
    private  JPanel checkoutPanel = new JPanel();
    public static JFrame frame = new JFrame("Checkout Window");
   static ArrayList <Product> toBuyList;//=new ArrayList<Product>(); 
   static Seller seller1,seller2;
   static String which;
    static final ArrayList <Integer> quantities=new ArrayList <Integer>();
    
    public CheckoutSystem(ArrayList <Product> toBuy,Seller seller_1,Seller seller_2,String who){
        frame.setResizable(false);
        toBuyList=toBuy;
        seller1=seller_1;
        seller2=seller_2;
        checkoutPanel.setLayout(new BorderLayout());
        which=who;
        
        JPanel textPanel = new JPanel();
        DefaultListModel model = new DefaultListModel();
       
        for (Product currentProduct:toBuy){
            model.addElement(currentProduct.getProductName()+"          $"+currentProduct.getSellPrice()+"       Available: "+currentProduct.getQuantity());
            quantities.add(1);
        }       
        
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

        textPanel.add(new JScrollPane(list));
        textPanel.add(ttlTxtt);
        
        int i=-1;
        double total=0;
        for (Product currentProduct:toBuyList){
                        i++;
                        total+=currentProduct.getSellPrice()*quantities.get(i);
                    }
                        DecimalFormat df = new DecimalFormat("#.##");
                        //toSet+="\nTOTAL:  $"+df.format(total);
                       ttlTxtt.setText("TOTAL:  $"+df.format(total));
                    
        
        
        
        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        JButton cancelButton = new JButton("Cancel");
        JButton payButton = new JButton("Pay Now");
        buttonPanel.add(cancelButton);
        buttonPanel.add(payButton);
        
        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                checkoutPanel.resize(1 ,1);
                StoreSystem regFace= new StoreSystem(which);
                regFace.setVisible(true);
                regFace.main();
            }
        });
        
        payButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //frame.dispose();
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
                if (Integer.parseInt(qtyTxt.getText())<0){
                                   JOptionPane.showMessageDialog(null,
                                           "Error: Please enter a non-negative number\n", "Error Massage",
                                           JOptionPane.ERROR_MESSAGE);
                               }  
                else if (Integer.parseInt(qtyTxt.getText())>toBuyList.get(index).getQuantity()){
                                   JOptionPane.showMessageDialog(null,
                                           "Error: Not enough available product, please buy up to the available quantity!", "Error Massage",
                                           JOptionPane.ERROR_MESSAGE);
                                           qtyTxt.setText(Integer.toString(quantities.get(index)));
                               }
                else{
                    quantities.set(index, Integer.parseInt(qtyTxt.getText()));
                    int i=-1;
                    double total=0;
                    for (Product currentProduct:toBuyList){
                        i++;
                        total+=currentProduct.getSellPrice()*quantities.get(i);
                    }
                        DecimalFormat df = new DecimalFormat("#.##");
                        //toSet+="\nTOTAL:  $"+df.format(total);
                       ttlTxtt.setText("TOTAL:  $"+df.format(total));
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
        
        JLabel label = new JLabel("Thank you for buying at Z-Bay!");
        label.setFont(new Font(label.getName(), Font.PLAIN, (int)label.getFont().getSize()+10));
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
        
        String input =  JOptionPane.showInputDialog(dialog,"Enter the Credit Card Number:");
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
//            checkoutPanel.resize(1, 1);
//            StoreSystem regFace= new StoreSystem(which);
//            regFace.setVisible(true);
//            regFace.main();
            
        }
        
        
    }
    
    public void main(ArrayList <Product> toBuy,Seller seller_1,Seller seller_2,String who){
        
        toBuyList=toBuy;
        seller1=seller_1;
        seller2=seller_2;
        which=who;
        
        frame.getContentPane().add(new CheckoutSystem(toBuy,seller1,seller2,which).getCheckoutPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
