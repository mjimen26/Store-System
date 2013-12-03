/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zack
 */
public class InventoryTest {
    
    

    /**
     * Test of addProduct method, of class Inventory.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        Product product = new Product();
        Inventory instance = new Inventory();
        boolean expResult = true;
        boolean result = instance.addProduct(product);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
}