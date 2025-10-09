package PROGRAMMING_CHALLENGE;

import java.util.Scanner;

public class PC012_sumAndAverageOfInputArrayNumbers {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int size;

        System.out.print("Enter size of an array: ");
        size = scan.nextInt();

        int[] number = new int[size];
        int sum = 0;
        for(int i = 0; i < number.length; i++){

            System.out.print("Enter a number: ");
            number[i] = scan.nextInt();
            sum += number[i];
        }
        System.out.println("The sum of all arrays: " + sum);
        System.out.println("The average pf all arrays: " + sum/number.length);


}}
