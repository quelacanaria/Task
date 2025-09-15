import static java.lang.Math.*;
public class Task9 {
    public static int add(int a, int b){
        return Math.addExact(a, b);
    }
    public static int subtract(int a, int b){
        return Math.subtractExact(a, b);
    }
    public static int multiply(int a, int b){
        return Math.multiplyExact(a, b);
    }
    public static int divide(int a, int b){
        return Math.divideExact(a, b);
    }
    public static void main(String[] args) {
        System.out.println("5+5="+add(5, 5));
        System.out.println("5-5="+subtract(5,5));
        System.out.println("5*5="+multiply(5,5));
        System.out.println("5/5="+divide(5,5));    
    }
}
