package TASK;

public class Task13 {
    public static void main(String[] args) {
        // Create Car object
        Car myCar = new Car("Toyota", "Camry", 2022, 4);

        // Display car details
        myCar.displayDetails();
    }
}
class Vehicle {
    protected String make;
    protected String model;
    protected int year;

    // Constructor
    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
}

// Derived class Car
class Car extends Vehicle {
    private int numberOfDoors;

    // Constructor
    public Car(String make, String model, int year, int numberOfDoors) {
        super(make, model, year); // Call Vehicle constructor
        this.numberOfDoors = numberOfDoors;
    }

    // Method to display details
    public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Number of Doors: " + numberOfDoors);
    }
}
