/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Administrator
 */
public class Seller extends User{
    
    Inventory inventory=new Inventory();
    final public int type=2;
    
    public Seller(String user,String pass){
        super.name=user;
        super.password=pass;
   
         try
      {
         FileInputStream fileIn = new FileInputStream(user+".cardei");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         inventory = (Inventory) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i)
          {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c)
      {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
       
        
        
        
    }
    public Boolean seri(){
        
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream(super.name+".cardei");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(inventory);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/employee.ser\n");
      }catch(IOException i)
      {
          i.printStackTrace();
      }
        
        return true;
    }
    
    public static void main(String [] args){
        
        System.out.println("hello cardei");
    }
    
}
