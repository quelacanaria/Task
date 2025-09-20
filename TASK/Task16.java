package TASK;

interface Animal {
    boolean feed(boolean timeToEat);
    void groom();
    void pet();
}

// Gorilla class implementing Animal interface
class Gorilla implements Animal {

    @Override
    public boolean feed(boolean timeToEat) {
        // put gorilla food into cage
        if (timeToEat) {
            System.out.println("Feeding the gorilla...");
            return true;
        } else {
            System.out.println("Not feeding time.");
            return false;
        }
    }

    @Override
    public void groom() {
        // lather, rinse, repeat
        System.out.println("The gorilla is grooming itself.");
    }

    @Override
    public void pet() {
        // pet at your own risk
        System.out.println("Warning! Petting gorilla is dangerous!");
    }
}

// Main class to test Gorilla
public class Task16 {
    public static void main(String[] args) {
        Gorilla g = new Gorilla();

        g.feed(true);   // feeding gorilla
        g.groom();      // grooming
        g.pet();        // warning message
    }
}
