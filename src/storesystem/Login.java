/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;
import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame {
    JTextField txuser = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);
    
    public static void main(String args) {
        Login login = new Login();
    }
    public String[] log(String message){
        String[] userpass=new String[2];
        //Login login = new Login();
        userpass[0]=txuser.getText();
        System.out.println("i'm here");
        userpass[1]=pass.getText();
        return userpass;
    }
    JButton blogin = new JButton("Login");
    JPanel panel = new JPanel();
    //JTextField txuser = new JTextField(15);
    //JPasswordField pass = new JPasswordField(15);

    Login(){
        super("Login Authentication");
        setSize(300,200);
        setLocation(500,280);
        panel.setLayout (null); 

        txuser.setBounds(70,30,150,20);
        pass.setBounds(70,65,150,20);
        blogin.setBounds(110,100,80,20);

        panel.add(blogin);
        panel.add(txuser);
        panel.add(pass);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
    }

     public static void main(String[] args) {
        Login logscreen=new Login();}
    
    
    public void actionlogin(){
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            String puname = txuser.getText();
            String ppaswd = pass.getText();
            
            if(puname.equals("Marvin") && ppaswd.equals("icecream")) {
                StoreSystem regFace = new StoreSystem("Marvin");
                regFace.setVisible(true);
                regFace.main();               
                dispose();
            } 
            else if (puname.equals("one") && ppaswd.equals("password")) {
                StoreSystem regFace= new StoreSystem("one");
                regFace.setVisible(true);
                regFace.main();
                dispose();
            } 
            else if (puname.equals("two") && ppaswd.equals("password")) {
                StoreSystem regFace = new StoreSystem("two");
                regFace.setVisible(true);
                regFace.main();
                dispose();
            } 
            else {
                JOptionPane.showMessageDialog(null,"Wrong Password / Username");
                txuser.setText("");
                pass.setText("");
                txuser.requestFocus();
            }
            }
        });
    }//end of actionlogin
    
}//end of code