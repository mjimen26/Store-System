
package storesystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *  A Seller is a user who can add Products to the Inventory, update an
 *      existing Product, and review their statistics
 */
public class Seller extends User{
    
    //Create an inventory
    Inventory inventory=new Inventory();
    
    
    /**
     * Constructs a Seller
     * @param user the username
     * @param pass the password
     */
    public Seller(String user,String pass){
        super.name=user;
        super.password=pass;
        super.type=2;
        this.doDeSerialization();
    }
    
    /**
     * De-serializes the Inventory 
     * @pre-condition: There is a .seri file created
     * @post-condition: De-serialized the file.
     */
    public void doDeSerialization(){
        try
        {
            FileInputStream fileIn = new FileInputStream(this.name+".seri");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            inventory = (Inventory) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("successful de-serialization of seller " + 
                    super.name+"!  total products: " + 
                    inventory.inventory.size());
         
            //Check if the Products are serialize using the println
            for (Product pro: inventory.inventory)
                System.out.println(pro.toString());
         
        }catch(IOException i){
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c){
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        } 
    }
    
    /**
     * Serializes the file
     * @return boolean
     */
    public Boolean seri(){
        try
        {
            FileOutputStream fileOut =
            new FileOutputStream(super.name+".seri");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(inventory);
            out.close();
            fileOut.close();
        }catch(IOException i){
            i.printStackTrace();
        }
        
        return true;
    }
}
