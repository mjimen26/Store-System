/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

/**
 *
 * @author Administrator
 */
public class Customer extends User {
    
    public Customer(String user,String pass){
        super.name=user;
        super.type=1;
        super.password=pass;
    }
    
    
}
