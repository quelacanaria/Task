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
}