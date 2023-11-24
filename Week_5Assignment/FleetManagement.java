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
    public String GetVehicleBrand() {return VehicleBrand;}
    public int GetVehicleID () {return VehicleID;}
    public double GetVehiclePrice () {return VehiclePrice;}
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

    public double GetVehicleCapacity() {return TruckCargoCapacity;}
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
    private double DroneMaxAltitude, DroneFlyingTime, DroneCameraRsolution;
    
    //Constructor: 
    public Drone(int ID, String Brand, double Price, double MaxAltitude, double FlyingTime, int CameraResolution) {
        super(ID, Brand, Price);
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
}

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
}

class VehicleSystem {
    public Map<Integer, Vehicle> MapOfVehiclesOwnedbyTheDealerShip = new LinkedHashMap<>();
    private Map<Integer, DealerShipCustomer> MapOfCustomers = new LinkedHashMap<>();
    private int CurrentCustomerID;
    private double NetValue;
    private double NetCapacity;

    public VehicleSystem () {CurrentCustomerID = 1; NetValue = 0;}
    
    public void AddVehicle(String Code, int ID, String Brand, double Price, String[] OtherParameters) {
        Vehicle NewVehicle;
        
        if(Code == "c") {
            NewVehicle = new Car(ID, Brand, Price, OtherParameters[0], OtherParameters[1], OtherParameters[2]);
            MapOfVehiclesOwnedbyTheDealerShip.put(ID, NewVehicle);
            NetValue += NewVehicle.GetVehiclePrice() ;
        }
        else if(Code == "t") {
            NewVehicle = new Car(ID, Brand, Price, 
                    Integer.parseInt(OtherParameters[0]),
                    Double.parseDouble(OtherParameters[1]),
                    Double.parseDouble(OtherParameters[2]),
                    Double.parseDouble(OtherParameters[3])
                );
            MapOfVehiclesOwnedbyTheDealerShip.put(ID, NewVehicle);NetValue += NewVehicle.GetVehiclePrice() ;
            NetCapacity += NewVehicle.getCapacity();
        }
        else if(Code == "b") {
            NewVehicle = new Bicycle(ID, Brand, Price, 
                    (OtherParameters[0]),
                    (OtherParameters[1]),
                    Integer.parseInt(OtherParameters[2])
                );
            MapOfVehiclesOwnedbyTheDealerShip.put(ID, NewVehicle);NetValue += NewVehicle.GetVehiclePrice() ;
        }
        else if(Code == "d") {
            NewVehicle = new Drone(ID, Brand, Price, 
                    Double.parseDouble(OtherParameters[0]),
                    Double.parseDouble(OtherParameters[1]),
                    Integer.parseInt(OtherParameters[2])
                );
            MapOfVehiclesOwnedbyTheDealerShip.put(ID, NewVehicle);NetValue += NewVehicle.GetVehiclePrice() ;
        }
    }

    public void AddCustomer() {
        DealerShipCustomer NewCutomer = new DealerShipCustomer(CurrentCustomerID++);
        MapOfCustomers.put(CurrentCustomerID - 1, NewCutomer);
    }

    public String FleetStatistics() {
        String ans = "Total Value of All Vehicles: " + NetValue + "\n";
        ans += "Total Cargo Capacity of Trucks: " + NetCapacity + "\n";
        return ans;
    }

    public void AddRental(int CustomerID, int VehicleID, int days) {
        String Brand = MapOfVehiclesOwnedbyTheDealerShip.get(i),GetVehicleBrand();
        String Price = 
        MapOfCustomers.get(CustomerID).AddRental(VehicleID, MapOfVehiclesOwnedbyTheDealerShip.get(VehicleID).GetVehicleBrand(), );
    }
}
