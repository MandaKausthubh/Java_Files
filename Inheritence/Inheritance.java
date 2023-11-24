
class Vehical {
    public double Speed;
    public int PassengerCapacity;

    public Vehical(float speed, int count) {
        Speed = speed;
        PassengerCapacity = count;
    }

    public void Display() {System.out.println("Hi");}
};


class Car extends Vehical {
    public int NumberOfDoors;
    public double HorsePower;

    public Car(int NumberOfDoors, int HP) {
        super(60, 4);
        this.NumberOfDoors = NumberOfDoors;
        this.HorsePower = HP;
    }

    public void Display() {System.out.println("Hello");}
};




