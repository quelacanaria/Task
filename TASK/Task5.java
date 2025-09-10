<<<<<<< HEAD
package TASK;
import java.util.*;
public class Task5 {
    public static void main(String[] args) {
        System.out.println("Enter 3 numbers: ");
        Scanner input = new Scanner(System.in);
        int[] numbers = new int[3];

        for(int x=0;x<numbers.length;x++){
            numbers[x] = input.nextInt(); 
        }
        Arrays.sort(numbers);
        for(int number: numbers){  
            System.out.print(number+" , ");
        }
    }
}

=======
package TASK;
import java.util.*;
public class Task5 {
    public static void main(String[] args) {
        System.out.println("Enter 3 numbers: ");
        Scanner input = new Scanner(System.in);
        int[] numbers = new int[3];

        for(int x=0;x<numbers.length;x++){
            numbers[x] = input.nextInt(); 
        }
        Arrays.sort(numbers);
        for(int number: numbers){  
            System.out.print(number+" , ");
        }
    }
}

>>>>>>> a02cd7bc28b031a908f2aeca9c905e873f043451
