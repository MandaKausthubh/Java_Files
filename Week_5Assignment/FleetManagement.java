// First is the File explaining the base class.

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class Vehicle {
    private String VehicleBrand;
    private int VehicleID;
    private double VehiclePrice;

    // Constructors:
    public Vehicle(int ID, String Brand, double Price) {
        VehicleBrand = Brand;
        VehicleID = ID;
        VehiclePrice = Price;
    }

    // Getters:
    private String GetVehicleBrand() {return VehicleBrand;}
    private int GetVehicleID () {return VehicleID;}
    private double GetVehiclePrice () {return VehiclePrice;}
};

class Car extends Vehicle {
    private String CarType;
    private String CarFuel;
    private String CarTransmission;

    // Constructor:
    public Car(int ID, String Brand, double Price, String Type, String Fuel, String Transmission) {
        super(ID, Brand, Price);
        CarType = Type;
        CarFuel = Fuel;
        CarTransmission = Transmission;
    }
}

class Truck extends Vehicle{
    private int TruckNumberOfAxels;
    private double TruckCargoCapacity, TruckBedLength, TruckFuelEfficiency;
    
    // Constructor:
    public Truck(int ID, String Brand, double Price, int NumberOfAxels,  double CargoCapacity, double BedLength, double FuelEfficiency) {
        super(ID, Brand, Price);
        TruckBedLength = BedLength;
        TruckCargoCapacity = CargoCapacity;
        TruckFuelEfficiency = FuelEfficiency;
        TruckNumberOfAxels = NumberOfAxels;
    }
};

class Bicycle extends Vehicle {
    private String BicycleType, BicycleFrame;
    private int BicycleNumberOfGears;

    //Constructors:
    public Bicycle(int ID, String Brand, double Price, String Type, String Frame, int NumberOfGears) {
        super(ID, Brand, Price);
        BicycleFrame = Frame;
        BicycleNumberOfGears = NumberOfGears;
        BicycleType = Type;
    }
};

class Drone extends Vehicle {
    private double DroneMaxAltitude, DroneFlyingTime;
    
    //Constructor: 
    public Drone(int ID, String Brand, double Price, double MaxAltitude, double FlyingTime) {
        super(ID, Brand, Price);
        DroneMaxAltitude = MaxAltitude;
        DroneFlyingTime = FlyingTime;
    }
};

class Rent {
    private int VID;
    private String Brand;
    private double Price;
    private int day;

    public Rent(int V, String B, double P, int D) {VID = V; Brand = B; Price = P; day = D;}

    public String DisplayRent() {return "- Vehicle ID: " + VID + ", Brand: "+ Brand + ", Rental Duration: " + day + " days, Rental Cost: " + Price + "\n";}
}

class DealerShipCustomer {
    private int CustomerID;
    private Set<Integer> VehiclesBorrowedByCustomer;
    public DealerShipCustomer(int ID) {
        CustomerID = ID;
        VehiclesBorrowedByCustomer = new LinkedHashSet<>();
    }
}

class VehicleSystem {
    public Map<Integer, Vehicle> MapOfVehiclesOwnedbyTheDealerShip = new LinkedHashMap<>();
    private Map<Integer, DealerShipCustomer> MapOfAllCustomers = new LinkedHashMap<>();
    
    
}
