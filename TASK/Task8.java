package TASK;
import java.util.*;
public class Task8 {
    static void sumUp(int... value){
        int sum = 0;
        for(int parameter: value){
            sum+=parameter;
        }
        System.out.println("Sum of All Parameters: ");
        System.out.println(sum);
        System.out.println("Parameter SumUp: ");
        int totalCumulativeSum =0;
        for(int parameter: value){
            int cumulative = 0;
            System.out.print(parameter+"(");
            for(int x=1;x<=parameter;x++){
                cumulative+=x;
                System.out.print(x);
            }
            totalCumulativeSum+=cumulative;
            System.out.println(")="+cumulative);
        }
        
        System.out.println("Total Values:");
        System.out.println(totalCumulativeSum);
    }
    public static void main(String[] args) {
        sumUp(4,5,10);
        
    }
}
