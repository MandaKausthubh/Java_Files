// First is the File explaining the base class.

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Vehicle {
    private String VehicleBrand;
    private int VehicleID;
    private double VehiclePrice;

    // Constructors:
    public Vehicle(int ID, String Brand, double Price, double Rental) {
        VehicleBrand = Brand;
        VehicleID = ID;
        VehiclePrice = Price;
    }

    // Getters:
    public String GetVehicleBrand() {return VehicleBrand;}
    public int GetVehicleID () {return VehicleID;}
    public double GetVehiclePrice () {return VehiclePrice;}
};

class Car extends Vehicle {
    private String CarType;
    private String CarFuel;
    private String CarTransmission;

    // Constructor:
    public Car(int ID, String Brand, double Price, double Rental, String Type, String Fuel, String Transmission) {
        super(ID, Brand, Price, Rental);
        CarType = Type;
        CarFuel = Fuel;
        CarTransmission = Transmission;
    }
}

class Truck extends Vehicle{
    private int TruckNumberOfAxels;
    private double TruckCargoCapacity, TruckBedLength, TruckFuelEfficiency;
    
    // Constructor:
    public Truck(int ID, String Brand, double Price, double Rental, int NumberOfAxels,  double CargoCapacity, double BedLength, double FuelEfficiency) {
        super(ID, Brand, Price, Rental);
        TruckBedLength = BedLength;
        TruckCargoCapacity = CargoCapacity;
        TruckFuelEfficiency = FuelEfficiency;
        TruckNumberOfAxels = NumberOfAxels;
    }

    public double GetVehicleCapacity() {return TruckCargoCapacity;}
};

class Bicycle extends Vehicle {
    private String BicycleType, BicycleFrame;
    private int BicycleNumberOfGears;

    //Constructors:
    public Bicycle(int ID, String Brand, double Price, double Rental, String Type, String Frame, int NumberOfGears) {
        super(ID, Brand, Price, Rental);
        BicycleFrame = Frame;
        BicycleNumberOfGears = NumberOfGears;
        BicycleType = Type;
    }
};

class Drone extends Vehicle {
    private double DroneMaxAltitude, DroneFlyingTime, DroneCameraRsolution;
    
    //Constructor: 
    public Drone(int ID, String Brand, double Price, double Rental, double MaxAltitude, double FlyingTime, int CameraResolution) {
        super(ID, Brand, Price, Rental);
        DroneMaxAltitude = MaxAltitude;
        DroneFlyingTime = FlyingTime;
        DroneCameraRsolution = CameraResolution;
    }
};



class Rent {
    private int VID;
    private String Brand;
    private double Price;
    private int day;

    public Rent(int V, String B, double P, int D) {VID = V; Brand = B; Price = P; day = D;}

    public String DisplayRent() {return "- Vehicle ID: " + VID + ", Brand: "+ Brand + ", Rental Duration: " + day + " days, Rental Cost: " + Price + "\n";}
};



class DealerShipCustomer {
    private int CustomerID;
    private Set<Rent> VehiclesBorrowedByCustomer;
    public DealerShipCustomer(int ID) {
        CustomerID = ID;
        VehiclesBorrowedByCustomer = new LinkedHashSet<>();
    }
    public void AddRental(int VID, String Brand, int P, int D) {
        Rent NewRent = new Rent(VID, Brand, P, D);
        VehiclesBorrowedByCustomer.add(NewRent);
    }
    public String DisplayHistory() {
        String ans = "Customer " + CustomerID + " Rental Hitory:\n";
        for(Rent x: VehiclesBorrowedByCustomer) {
            ans += x.DisplayRent();
        }
        return ans;
    }
};




class VehicleSystem {
    public Map<Integer, Vehicle> MapOfVehiclesOwnedbyTheDealerShip = new LinkedHashMap<>();
    private Map<Integer, DealerShipCustomer> MapOfCustomers = new LinkedHashMap<>();
    private int CurrentCustomerID;
    private double NetValue;
    private double NetCapacity;

    public VehicleSystem () {CurrentCustomerID = 1; NetValue = 0;}
    
    // Method to add a vehicle
    public String AddVehicle(String Code, int ID, String Brand, double Price, double Rental, String[] OtherParameters) {
        Vehicle NewVehicle;
        if(Code == "c") {
            NewVehicle = new Car(ID, Brand, Price, Rental, OtherParameters[0], OtherParameters[1], OtherParameters[2]);
            MapOfVehiclesOwnedbyTheDealerShip.put(ID, NewVehicle);
            NetValue += NewVehicle.GetVehiclePrice() ;
            return "Car - ID:" + ID + "Brand: " + Brand + ", Price: " + Price + ", Rental Cost: " + 
                Rental + "/day, Type: " + OtherParameters[0] + ", Fuel: " +
                OtherParameters[1] + ", Transmission: " + OtherParameters[2] + "\n";
        }
        else if(Code == "t") {
            NewVehicle = new Car(ID, Brand, Price, Rental, 
                    Integer.parseInt(OtherParameters[0]),
                    Double.parseDouble(OtherParameters[1]),
                    Double.parseDouble(OtherParameters[2]),
                    Double.parseDouble(OtherParameters[3])
                );
            MapOfVehiclesOwnedbyTheDealerShip.put(ID, NewVehicle);NetValue += NewVehicle.GetVehiclePrice() ;
            NetCapacity += NewVehicle.getCapacity();
            return "Truck - ID: "+ID+", Brand: "+Brand+", Price: "+Price+", Rental Cost: "+Rental+"/day," + 
                   "Cargo Capacity: "+OtherParameters[1]+" kg, Bed Length: "+OtherParameters[2]+
                   " m, Axles: "+OtherParameters[0]+", Mileage: " + OtherParameters[3] + " miles/gallon";
        }
        else if(Code == "b") {
            NewVehicle = new Bicycle(ID, Brand, Price, 
                    (OtherParameters[0]),
                    (OtherParameters[1]),
                    Integer.parseInt(OtherParameters[2])
                );
            MapOfVehiclesOwnedbyTheDealerShip.put(ID, NewVehicle);NetValue += NewVehicle.GetVehiclePrice() ;
            return "Bicycle - ID: "+ID+", Brand: "+Brand+", Price: "+Price+", Rental Cost: "+Rental+"/day, Type: "+
                OtherParameters[0]+", Frame: " + OtherParameters[1] +
                ", Gears: " + OtherParameters[2] + "\n";
        }
        else if(Code == "d") {
            NewVehicle = new Drone(ID, Brand, Price, 
                    Double.parseDouble(OtherParameters[0]),
                    Double.parseDouble(OtherParameters[1]),
                    Integer.parseInt(OtherParameters[2])
                );
            MapOfVehiclesOwnedbyTheDealerShip.put(ID, NewVehicle);NetValue += NewVehicle.GetVehiclePrice() ;
            return "Drone - ID: "+ID+", Brand: "+Brand+", Price: "+Price+", Rental Cost: "+Rental+
                "/day, Max Altitude: "+OtherParameters[0]+" m, Flight time: "+
                OtherParameters[1]+" min, Camera Resolution: " + OtherParameters[2] +" MP";
        }
    }
    
    // Method to add Customer
    public String AddCustomer() {
        DealerShipCustomer NewCutomer = new DealerShipCustomer(CurrentCustomerID++);
        MapOfCustomers.put(CurrentCustomerID - 1, NewCutomer);
        return "Customer " + (CurrentCustomerID - 1) + " added";
    }
    
    //Method to show fleet Statistics
    public String FleetStatistics() {
        String ans = "Total Value of All Vehicles: " + NetValue + "\n";
        ans += "Total Cargo Capacity of Trucks: " + NetCapacity + "\n";
        return ans;
    }

    // Method to add a rental service provided
    public void AddRental(int CustomerID, int VehicleID, int days) {
        String Brand = MapOfVehiclesOwnedbyTheDealerShip.get(i).GetVehicleBrand();
        String Price = MapOfVehiclesOwnedbyTheDealerShip.get(i).GetVehiclePrice();
        MapOfCustomers.get(CustomerID).AddRental(VehicleID, Brand, Price, days);
    }

    // Method to retrieve a customers History
    public String GetCustomerHistroy(int ID) {
        return MapOfCustomers.get(ID).DisplayHistory();
    }
};

class FleetManagement {
    public static void Main(String[] args) {
        VehicleSystem RentalService = new VehicleSystem();
        Scanner Scan = new Scanner(System.in);
        String S, ans = "";
        while(true) {
            S = Scan.nextLine();
            String Command = S.split(" ", 2)[0];
            if(Command == "END") {break;}
            else if(Command == "ADD_CUSTOMER") {ans += RentalService.AddCustomer();}
            else if(Command == "FLEET_STATISTICS") {ans += RentalService.FleetStatistics();}
            else if(Command == "RENT") {}
        }
        System.out.print(ans);
        Scan.close();
    }
}











