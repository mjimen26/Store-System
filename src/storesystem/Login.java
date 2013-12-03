
package storesystem;
import javax.swing.*;
import java.awt.event.*;

/**
 * This is the class the starts the application.
 * It has two text fields for username and password.
 * And it also has a Log In button which calls a new
 * StoreSystem.
 */
public class Login extends JFrame {
    JTextField txuser = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);      
    JButton blogin = new JButton("Login");
    JPanel panel = new JPanel();
    
    /**
     * Construct the Login Window
     */
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
    
    /**
     * log method 
     * @param message
     * @return 
     */
    public String[] log(String message){
        String[] userpass=new String[2];
        userpass[0]=txuser.getText();
        //Test the use if logged in
        System.out.println("I'm here");
        userpass[1]=pass.getText();
        return userpass;
    }
    
    /**
     * Implements the listener for the log in process.
     * Compares the entered username and password fields to the
     *      hard-coded credentials.
     * @pre-condition: the username and password fields have values
     * @post-condition: the username and password fields have been validated
     *      and the respective StoreSystem Window is displayed (Customer/Seller)
     */
    public void actionlogin(){
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            String puname = txuser.getText();
            String ppaswd = pass.getText();
            
            //If a customer logs in, display a Customer Window
            if(puname.equals("Marvin") && ppaswd.equals("icecream")) {
                StoreSystem regFace = new StoreSystem("Marvin");
                regFace.setVisible(true);
                regFace.main();               
                dispose();
            } 
            //otherwise, display a Seller Window
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
            //if any of the fields are invalid, display an error message
            else {
                JOptionPane.showMessageDialog(null,"Wrong Password / Username");
                txuser.setText("");
                pass.setText("");
                txuser.requestFocus();
            }
            }
        });
    }//end of actionlogin
    
    /**
     * The Main Methods which starts the program.
     * @param args 
     */
     public static void main(String[] args) {
        Login logscreen=new Login();}
    
}//end of code



    
//    public static void main(String args) {
//        Login login = new Login();
//    }