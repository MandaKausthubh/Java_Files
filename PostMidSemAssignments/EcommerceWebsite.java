/*
#include <iostream>
#include <map>
#include <set>
#include <string>
#include <unordered_map>
#include <vector>
*/

import java.util.*;

// Class Of Products:
class Product{
        private int ProductID;
        private String ProductName;
        private double price;


        public static Map<Integer, Product> UniversalMapOfProducts = new LinkedHashMap<>();        
        //Getters:

        public int GetProductID(){return this.ProductID;}
        public String GetProductName(){return this.ProductName;}
        public double GetPrice() {return this.price;}

        //Constructors:

        public Product() {}

        public Product(int ID, String Name, double Price){
            this.ProductID = ID;
            this.ProductName = Name;
            this.price = Price;
            Product.UniversalMapOfProducts.put(ID, this);
        }

        //Display Product:
        void DisplayProduct() {
            System.out.println("Product Name: "+ ProductName);
            System.out.println("Product ID: "+ ProductID);
            System.out.println("Price: "+ price);
        }
};


class CartItem{
        private int ProductID;
        private int Quantity;
    
        //Getters:
        public int GetCartItemID() {return ProductID;}
        public int GetQuantity() {return Quantity;}

        // Constructors:
        public CartItem(int ID, int Q) {
            ProductID = ID;
            Quantity = Q;
        }

        // Function to calculate the cost of the CartItem;
        public double NetCost() {return Quantity * Product.UniversalMapOfProducts.get(this.ProductID).GetPrice();}

        //DisplayCartItem:
        public void DisplayCartItem() {
            Product.UniversalMapOfProducts.get(this.ProductID).DisplayProduct();
            System.out.println(Quantity);
        }
};

class ShoppingCart{
    
        private int ShoppingCartID;
        private Map<Integer, CartItem> SetOfAllCartItems = new LinkedHashMap<>();

        // Getters:
        public int GetShoppingCartId() {return ShoppingCartID;}
        
        // AddCartItem: Function To Add a new CartItem to Shopping Cart;
        public void AddCartItem(int ID, int Quant) {
            CartItem newCartItem = new CartItem(ID, Quant);
            SetOfAllCartItems.put(ID ,newCartItem);
        }

        // RemoveCartItem: Function to Remove an existing Item from Cart;
        public void RemoveItem(int ID) {
            /*Map<Integer, CartItem>::iterator iter = SetOfAllCartItems.begin();
            for(;iter != SetOfAllCartItems.end(); iter++){
                CartItem temp = (*iter).second;
                if(temp.GetCartItemID() == ID){break;}
            }
            SetOfAllCartItems.erase(iter);*/
            
            SetOfAllCartItems.remove(ID);
        }
        
        // Method to calculate the cast of the Shopping Cart.
        public double CostOfShoppingCart() {
            double NetCost = 0;
            //Map<Integer, CartItem>::iterator iter = SetOfAllCartItems.begin();
            for(Map.Entry<Integer, CartItem> iter: SetOfAllCartItems.entrySet()){
                //CartItem Temp = (iter).second;
                NetCost += iter.getValue().NetCost();
            }
            return NetCost;
        }
        
        //Constructors:
        public ShoppingCart(int ID) {ShoppingCartID = ID;}

        public ShoppingCart() {}

       public  void DisplayShoppingCart() {
            System.out.println("ShoppingCart: "+ ShoppingCartID);
            for(Map.Entry<Integer, CartItem> x: SetOfAllCartItems.entrySet()) {
                x.getValue().DisplayCartItem(); 
                System.out.println("Total Cost: "+ CostOfShoppingCart());
            }
        }
};

class Customer{

        private int CustomerID;
        private Map<Integer, ShoppingCart> MapOfAllShoppingCarts = new LinkedHashMap<>();
        private String Name;
        private static int currentCustomerID=1;
        private int currentShoppingCart;

        
        // Customer  Constructors:
        public Customer(String CustName){
            CustomerID = currentCustomerID++;
            Name = CustName;
            System.out.println("Created Customer: "+ CustName+ "\tCustomer ID: "+ CustomerID);
            currentShoppingCart = 1;
        }

        public Customer() {this.CustomerID = currentCustomerID++;}

        // Adding a new ShoppingCart;
        public void AddNewShoppingCart() {
            ShoppingCart newShoppingCart = new ShoppingCart(currentShoppingCart++);
            MapOfAllShoppingCarts.put(currentShoppingCart - 1, newShoppingCart);
            System.out.println("Added new Shopping Cart to Customer: "+ CustomerID);
            System.out.println("New ShoppingCartID: "+ (currentShoppingCart - 1));
        }

        public int GetCustomerID() {return CustomerID;}

        public void AddItemToCart(int ShoppingCartID, int ItemID, int quant) {
            MapOfAllShoppingCarts.get(ShoppingCartID).AddCartItem(ItemID, quant);
        }

        public void RemoveItemFromShoppingCart(int ShoppingCartID, int ProductID){
            MapOfAllShoppingCarts.get(ShoppingCartID).RemoveItem(ProductID);
        }

        public void DisplayCustomer() {
            System.out.println("Name : " + Name + '\n' + "CustomerID: " + CustomerID);
            for(Map.Entry<Integer, ShoppingCart> entry: MapOfAllShoppingCarts.entrySet()) {
                entry.getValue().DisplayShoppingCart();
            }
        }

        public void SetCustomerID(int ID) {this.CustomerID = ID;}

};

class CustomerManager{
        private Map<Integer, Customer> MapOfAllCustomers = new LinkedHashMap<>();

        //Method to add a customers:
        public void AddCustomer(String name) {
            Customer newCustomer = new Customer(name);
            MapOfAllCustomers.put(newCustomer.GetCustomerID(), newCustomer);
        }

        public void AddCustomer(int ID) {
            Customer newCustomer = new Customer(name);
            MapOfAllCustomers.put(newCustomer.GetCustomerID(), newCustomer);
        }

        public void AddShoppingCartToCustomer (int CustomerID) {
            MapOfAllCustomers.get(CustomerID).AddNewShoppingCart();
        }

        public void AddCartItemToShoppingCart (int CustomerID, int ShoppingCartID, int itemID, int quant) {
            MapOfAllCustomers.get(CustomerID).AddItemToCart(ShoppingCartID, itemID, quant);
        }

        public void RemoveItemFromShoppingCart(int CustomerID, int ShoppingCartID, int itemID) {
            MapOfAllCustomers.get(CustomerID).RemoveItemFromShoppingCart(ShoppingCartID, itemID);
        }

        public void Display() {
            for(Map.Entry<Integer, Customer> entry: MapOfAllCustomers.entrySet()) {
                //x.second.DisplayCustomer();
                entry.getValue().DisplayCustomer();
                System.out.println('\n'+ '\n');
            }
        }
};


class EcommerceWebsite{
    public static void main(String args[]) {
        /*Product Laptop1 = new Product(101, "MI Notebook", 40000);
        Product Laptop2 = new Product(102, "MacBook", 120000);
        Product MobilePhone1 = new Product(103, "Samsung Galaxy", 20000);

        for(Map.Entry<Integer, Product> Entry: Product.UniversalMapOfProducts.entrySet()){
            Entry.getValue().DisplayProduct();
            System.out.println();
        }
        
        CustomerManager Platform = new CustomerManager();
        Platform.AddCustomer("Jinesh Pagaria");
        Platform.AddCustomer("Rohan Rajesh");

        System.out.println();

        Platform.AddShoppingCartToCustomer(1000);
        Platform.AddShoppingCartToCustomer(1001);

        System.out.println();

        Platform.AddCartItemToShoppingCart(1000, 1, 101, 2);
        Platform.AddCartItemToShoppingCart(1001, 1, 102, 3);
        Platform.AddCartItemToShoppingCart(1001, 1, 102, 1);

        Platform.Display();*/

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        CustomerManager IIITBCustomer = new CustomerManager();
        scan.nextLine();
        String[] str = scan.nextLine().split(" ", n);
        
        
        int c = scan.nextInt();

        for(int i = 0; i < c; i++) {
            String[] S = scan.nextLine().split(" ", 6);
            if(S.length == 2) {
                int CustID = Integer.parseInt(S[0]);
                
            }

        }

        scan.close();
    }
};


















