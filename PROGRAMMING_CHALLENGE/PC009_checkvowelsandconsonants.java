package PROGRAMMING_CHALLENGE;

import java.util.Scanner;

public class PC009_checkvowelsandconsonants {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);


        System.out.print("Enter a letter: ");
        String letter = scan.nextLine().toUpperCase();

        switch(letter){

            case "A","E","I","O","U"
                    -> System.out.println(letter + " is a VOWEL");
            case "B","C","D","F","G","H","J","K","L","M","N","P","Q",
                 "R","S","T","V","W","X","Y","Z"
                  -> System.out.println(letter + " is a CONSONANT");
            default -> System.out.println("It is not a letter.");
        }
}}
