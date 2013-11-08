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
    final public int type=1;
    public Customer(String user,String pass){
        super.name=user;
        super.password=pass;
    }
    
    
}
