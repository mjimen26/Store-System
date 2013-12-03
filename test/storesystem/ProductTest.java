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
public class ProductTest {
    
    public ProductTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSellPrice method, of class Product.
     */
    @Test
    public void testGetSellPrice() {
        System.out.println("getSellPrice");
        Product instance = new Product("ID", "NAME",1.00,2.00,5,"www.google.com","DESCRIPTION");
        double expResult = 2.00;
        double result = instance.getSellPrice();
        assertEquals(expResult, result, 1.00);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getQuantity method, of class Product.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        Product instance = new Product("ID", "NAME",1.00,2.00,5,"www.google.com","DESCRIPTION");
        int expResult = 5;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getProductID method, of class Product.
     */
    @Test
    public void testGetProductID() {
        System.out.println("getProductID");
        Product instance = new Product("ID", "NAME",1.00,2.00,5,"www.google.com","DESCRIPTION");
        String expResult = "ID";
        String result = instance.getProductID();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getProductName method, of class Product.
     */
    @Test
    public void testGetProductName() {
        System.out.println("getProductName");
        Product instance = new Product("ID", "NAME",1.00,2.00,5,"www.google.com","DESCRIPTION");
        String expResult = "NAME";
        String result = instance.getProductName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getInvoicePrice method, of class Product.
     */
    @Test
    public void testGetInvoicePrice() {
        System.out.println("getInvoicePrice");
        Product instance = new Product("ID", "NAME",1.00,2.00,5,"www.google.com","DESCRIPTION");
        double expResult = 1.0;
        double result = instance.getInvoicePrice();
        assertEquals(expResult, result, 1.0);
        
    }

    /**
     * Test of getDescription method, of class Product.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Product instance = new Product("ID", "NAME",1.00,2.00,5,"www.google.com","DESCRIPTION");
        String expResult = "DESCRIPTION";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPicture method, of class Product.
     */
    @Test
    public void testGetPicture() {
        System.out.println("getPicture");
        Product instance = new Product("ID", "NAME",1.00,2.00,5,"www.google.com","DESCRIPTION");
        String expResult = "www.google.com";
        String result = instance.getPicture();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getSold method, of class Product.
     */
    @Test
    public void testGetSold() {
        System.out.println("getSold");
        Product instance = new Product("ID", "NAME",1.00,2.00,5,"www.google.com","DESCRIPTION");
        int expResult = 0;
        int result = instance.getSold();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of sell method, of class Product.
     */
    @Test
    public void testSell() {
        System.out.println("sell");
        int x = 4;
        Product instance = new Product("ID", "NAME",1.00,2.00,5,"www.google.com","DESCRIPTION");
        instance.sell(4);
        
    }
}