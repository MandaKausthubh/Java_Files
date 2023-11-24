import java.util.Scanner;


class Product {
    private static int Comman_ID = 1000;
    private String productName;
    private double price;
    private int Unique_ID;

    Product () {
        this.Unique_ID = Comman_ID + 1;
        Comman_ID += 1;
        this.productName = "Unspecified";
        this.price = 0.0;
    }

    Product (String Product_name, double Price) {
        this.Unique_ID = Comman_ID + 1;
        Comman_ID += 1;
        this.productName = Product_name;
        this.price = Price;
    }

    Product (String Product_name) {
        this.Unique_ID = Comman_ID + 1;
        Comman_ID += 1;
        this.productName = Product_name;
        this.price = 0.0;
    }

    public void DisplayProduct() {
        System.out.println("Product Name: " + this.productName + "\nProduct ID :" + this.Unique_ID + "\nCosts: " + this.price + '\n');
    }
}

class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int test = scanner.nextInt();
        Product[] var = new Product [test];
        String Dummy = scanner.nextLine();
        for(int i = 0; i < test; i++) {
            int x  = 1;
            String PName = scanner.nextLine();
            if(!PName.isEmpty()) {
                x *= 2;
            }
            String Price = scanner.nextLine();
            if(!Price.isEmpty()) {
                x *= 3;
            }

            if(x == 6){
                double y = Double.parseDouble(Price);
                Product P = new Product(PName, y);
                //P.DisplayProduct();
                var[i] = P;
            }
            if(x == 2){
                Product P = new Product(PName);
                //P.DisplayProduct();
                var[i] = P;
            }
            if(x == 1){
                Product P = new Product();
                //P.DisplayProduct();
                var[i] = P; 
            }
        }

        for(int i = 0; i < test; i++) {
            var[i].DisplayProduct();
        }
    }
}