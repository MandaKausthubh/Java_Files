import java.util.*;

class CartItem{//Class to define an item
    private int price; 
    private int CartItemID;
    private int quantity;
    private String name;
    
    // Getters: 
    int GetProductID(){//getters
        return this.CartItemID;
    }

    int getQuantity(){
        return this.quantity;
    }

    CartItem(int price, int CartItemID, int quantity, String name){//constructor
        this.price = price;
        this.CartItemID = CartItemID;
        this.quantity = quantity;
        this.name=name;
    }

    

    void setQuantity(int q){
        this.quantity=q;
    }

    String getName(){
        return this.name;
    }

    int getPrice(){
        return this.price;
    }
}

class ShoppingCart{//Shopping cart with items in it, it is identified using a cart id
    private int customerID;
    private int cartID;
    private Vector<CartItem> items;

    ShoppingCart(int customerID, int cartsID){//Constructor
        this.customerID=customerID;
        this.cartID=cartsID;
        this.items= new Vector<CartItem>();
    }

    Vector<CartItem> getItems(){//getters
        return this.items;
    }

    int getCustomerID(){
        return this.customerID;
    }

    int getCartID(){
        return this.cartID;
    }

    void addItem(int price, int CartItemID, int quantity, String name){//add item into a cart
        CartItem it = new CartItem(price, CartItemID, quantity, name);
        this.items.add(it);
    }

    void removeItem(String name, int quantity){//remove item from a cart
        for(int i=0;i<items.size();i++){
            if(name.equals(items.get(i).getName())){
                if(quantity==items.get(i).getQuantity())
                    items.remove(i);
                else{
                    items.get(i).setQuantity(items.get(i).getQuantity()-quantity);
                }
            }
        }
    }

    void printItems(){//return the items in the cart
        for(int i=0;i<items.size();i++){
            System.out.print("Name: "+items.get(i).getName()+" ");
            System.out.print("Product ID: "+items.get(i).GetProductID()+" ");
            System.out.print("Price: "+items.get(i).getPrice()+" ");
            System.out.println("Quantity: "+items.get(i).getQuantity());
        }
    }

    int returnPrice(){//find out the total price in the cart
        int price=0;
        for(int i=0;i<this.items.size();i++){
            price+=this.items.get(i).getPrice()*this.items.get(i).getQuantity();
        }
        return price;
    }
}

class Customer{//customer who has multiple carts, identified by their customer id
    private int customerID;
    private Vector<ShoppingCart> carts;

    Customer(int customerID){//constructors
        this.customerID=customerID;
        this.carts=new Vector<ShoppingCart>();
    }

    Vector<ShoppingCart> getCarts(){//getters
        return this.carts;
    }

    int getCustomerID(){
        return this.customerID;
    }

    void newCart(ShoppingCart cart){//making a new cart
        this.carts.add(cart);
    }


}

class CustomerManager{//an overall manager for all the customers
    private Vector<Customer> customers = new Vector<Customer>();

    void addCustomer(int custID){//add a new customer
        customers.add(new Customer(custID));
    }

    void addCart(int custID,int cartsID){//add a cart into the customer's list of carts
        for(int i=0;i<customers.size();i++){
            if(customers.get(i).getCustomerID()==custID){
                ShoppingCart cart = new ShoppingCart(custID,cartsID);
                customers.get(i).newCart(cart);
            }
        }
    }

    ShoppingCart findCart(int custID, int cartID){//find a certain cart to add or remove an item
        for(int i=0;i<customers.size();i++){
            if(customers.get(i).getCustomerID()==custID){
                for(int j=0;j<customers.get(i).getCarts().size();j++){
                    if(customers.get(i).getCarts().get(j).getCartID()==cartID){
                        return customers.get(i).getCarts().get(j);
                    }
                }
            }
        }
        return customers.get(0).getCarts().get(0);
    }

    void returnCarts(int custID){//print all carts' items of the customer
        for(int i=0;i<customers.size();i++){
            if(customers.get(i).getCustomerID()==custID){
                for(int j=0;j<customers.get(i).getCarts().size();j++){
                    System.out.println("Cart "+customers.get(i).getCarts().get(j).getCartID());
                    customers.get(i).getCarts().get(j).printItems();
                }
            }
        }
    }

    Customer getCustomer(int custID){//find a customer object
        for(int i=0;i<customers.size();i++){
            if(customers.get(i).getCustomerID()==custID){
                return customers.get(i);
            }
        }
        return null;
    }

    int getTotalValue(int custID){//get the total value of all carts of a certain customer
        int price=0;
        for(int i=0;i<customers.size();i++){
            if(customers.get(i).getCustomerID()==custID){
                for(int j=0;j<customers.get(i).getCarts().size();j++){
                    price+=customers.get(i).getCarts().get(j).returnPrice();
                }
            }
        }
        return price;
    }
}

class Lab_3PartBDomJudge{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        Vector<Integer> numberOfCarts= new Vector<Integer>();
        CustomerManager man = new CustomerManager();
        for(int i=0;i<n;i++)    numberOfCarts.add(scan.nextInt());
        for(int i=1;i<n+1;i++){
            man.addCustomer(i);
            for(int j=1;j<numberOfCarts.get(i-1)+1;j++)  man.addCart(i, j);
        }
        int m=scan.nextInt();
        for(int i=0;i<m;i++){
            int customerID = scan.nextInt();
            String t = scan.next();
            if(t.matches("[0-9]+")){
                int cartID = Integer.parseInt(t);
                String temp = scan.next();
                if(temp.equals("total")){
                    System.out.println(man.getCustomer(customerID).getCarts().get(cartID-1).returnPrice());
                }
                if(temp.equals("add")){
                    String name = scan.next();
                    int price = scan.nextInt();
                    int quantity = scan.nextInt();
                    man.findCart(customerID, cartID).addItem(price, 0, quantity, name);
                }
                if(temp.equals("remove")){
                    String name = scan.next();
                    int quantity = scan.nextInt();
                    man.findCart(customerID, cartID).removeItem(name, quantity);
                }
            }
            else{
                System.out.println(man.getTotalValue(customerID));
            }
        }

        scan.close();

    }
}
