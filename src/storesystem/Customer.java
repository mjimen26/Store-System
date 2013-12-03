
package storesystem;

/**
 * Customer is a user who purchases products from the store.
 */
public class Customer extends User {
    
    /**
     * Constructs a Customer
     * @param user the username
     * @param pass the password
     */
    public Customer(String user,String pass){
        super.name=user;
        super.type=1;
        super.password=pass;
    }
}
