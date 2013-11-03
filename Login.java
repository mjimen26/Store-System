/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;
import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame {

    public static void main(String[] args) {
        Login login = new Login();
    }

    JButton blogin = new JButton("Login");
    JPanel panel = new JPanel();
    JTextField txuser = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);

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

    public void actionlogin(){
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            String puname = txuser.getText();
            String ppaswd = pass.getText();
            if(puname.equals("test") && ppaswd.equals("123")) {
                StoreSystem regFace = new StoreSystem();
                regFace.setVisible(false);
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