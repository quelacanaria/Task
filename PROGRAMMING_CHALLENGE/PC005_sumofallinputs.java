package PROGRAMMING_CHALLENGE;

import java.util.Scanner;

public class PC005_sumofallinputs {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int size;

        System.out.print("Enter the max index of your array: ");
        size = scan.nextInt();

        int[] number = new int[size];

        int sum = 0;

        for(int i = 0; i < number.length; i++){

            System.out.print("Input an integer: ");
            number[i] = scan.nextInt();

            sum += number[i];
        }

        System.out.println("sum of all integer is: " + sum);
    }

}
