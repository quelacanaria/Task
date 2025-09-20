package TASK;
abstract class abstractClass {
    abstract void abstractMethod();

    void concreteMethod() { // concrete methods are still allowed in abstract classes
        System.out.println("This is a concrete method.");
    }
}

class B extends abstractClass {
    void abstractMethod() {
        System.out.println("B's implementation of abstractMethod.");
    }
}

class C extends abstractClass {
    @Override
    void abstractMethod() {
        System.out.println("C's implementation of abstractMethod.");
    }
}

public class Task14 {
    public static void main(String[] args) {
        B b = new B();
        b.abstractMethod();   // B's implementation
        b.concreteMethod();   // inherited from abstractClass

        C c = new C();
        c.abstractMethod();   // C's implementation
        c.concreteMethod();   // inherited from abstractClass
    }
}
