<<<<<<< HEAD
package TASK;
import java.util.*;
public class Task4 {
    public static void main(String[] args) {
        System.out.println("Type any to check if it is a palindrome or not: ");
        Scanner input = new Scanner(System.in);
        String type = input.nextLine();
        String reverse = ""; 
        StringBuilder palindrome = new StringBuilder((type.toLowerCase()));
        for(int x=0;x<palindrome.length();x++){
            reverse = palindrome.charAt(x)+reverse;
        }
        if ((palindrome.toString()).equals(reverse)) {
            System.out.println("Palindrome");
        }else{  
            System.out.println("Not Palindrome"); 
        }
    }
=======
package TASK;
import java.util.*;
public class Task4 {
    public static void main(String[] args) {
        System.out.println("Type any to check if it is a palindrome or not: ");
        Scanner input = new Scanner(System.in);
        String type = input.nextLine();
        String reverse = ""; 
        StringBuilder palindrome = new StringBuilder((type.toLowerCase()));
        for(int x=0;x<palindrome.length();x++){
            reverse = palindrome.charAt(x)+reverse;
        }
        if ((palindrome.toString()).equals(reverse)) {
            System.out.println("Palindrome");
        }else{  
            System.out.println("Not Palindrome");
        }
    }
>>>>>>> a02cd7bc28b031a908f2aeca9c905e873f043451
}