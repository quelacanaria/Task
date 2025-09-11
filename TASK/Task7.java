package TASK;

import java.util.*;

public class Task7 {
    static int addTwoNum(int a, int b){
        return a+b;
    }
    static int multiplyTwoNum(int a, int b){ 
        return a*b;
    }
    static int subTwoNum(int a, int b){
        return a-b;
    } 
    static int divideTwoNum(int a, int b){
        return a/b;
    }
      public static void main(String[] args) {
        System.out.println("Enter two numbers");
        Scanner input = new Scanner(System.in);
        int[] type = new int[2];
        
        for(int x=0;x<type.length;x++){
            type[x]=input.nextInt();
        } 
        
        System.out.println("a+b="+addTwoNum(type[0], type[1]));
        System.out.println("a*b="+multiplyTwoNum((type[0]), type[1]));
        System.out.println("a-b="+subTwoNum(type[0], type[1]));
        System.out.println("a/b="+divideTwoNum(type[0], type[1]));
      }
}


    
