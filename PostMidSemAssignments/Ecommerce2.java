import java.util.LinkedHashMap;
import java.util.Map;

class CartItem {
    private int CartItemID;
    private String CartItemName;
    private int CartItemPrice;
    private int Quantity;
    
    // Getters for this class:
    public void SetCartItemID(int ID) {this.CartItemID = ID;}
    public void SetName(String Name) {this.CartItemName = Name;}
    public void SetPrice(int P) {this.CartItemPrice = P;}
    public void SetQuantity(int Q) {this.Quantity = Q;}
    
    // Setters for this Class:
    public int GetCartItemID() {return this.CartItemID;}
    public int GetCartItemPrice() {return this.CartItemPrice;}
    public String GetCartItemName() {return this.CartItemName;}
    public int GetCartItemQuantity() {return this.Quantity;}

    // Constructor:
    public CartItem( int ID, int Price, int Q, String NAME) {
        this.Quantity = Q; this.CartItemPrice = Price; this.CartItemName = NAME; this.CartItemID = ID;
    }
};

class ShoppingCart {
    private int ShoppingCartID;
    private int TotalPrice;
    private Map<Integer, CartItem> MapOfAllShoppingCartItems = new LinkedHashMap<>();
    
    // SETTERS:
    public void SetShoppingCartID(int ID) {this.ShoppingCartID = ID;}

    // Getters:
    public int GetShoppingCartID() {return this.ShoppingCartID;}
    public int GetTotalPrice() {return this.TotalPrice;}

    // Method to Add Items to Shopping Cart:
    public void AddItemToCart(int ID, String Name, int  Quantity, int Price) {
        CartItem NewCartItem = new CartItem(ID, Price, Quantity, Name);
        MapOfAllShoppingCartItems.put(ID, NewCartItem);
        TotalPrice += Price;
    }
}
