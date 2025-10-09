package PROGRAMMING_CHALLENGE;

import java.util.Scanner;

public class PC001_GradeAF {

    public static void main(String[] args){

        int score;

        Scanner scan = new Scanner(System.in);

        System.out.printf("\n Enter your score: \n");
        score = scan.nextInt();

        if(score >= 90){
            System.out.printf("\n Your grade is %d so you got an A ", score);
        }else if(score >= 80){
            System.out.printf("\n Your grade is %d so you got an B", score);
        }else if(score >=70){
            System.out.printf("\n Your grade is %d so you got an C", score);
        }else if(score >=60){
            System.out.printf("\n Your grade is %d so you got an D", score);
        }else{
            System.out.printf("\n Your grade is %d so you got an F", score);
        }

        scan.close();

    }

} 
