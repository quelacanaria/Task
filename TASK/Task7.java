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
        Task7 t7 = new Task7();
        System.out.println("a+b="+t7.addTwoNum(type[0], type[1]));
        System.out.println("a*b="+t7.multiplyTwoNum((type[0]), type[1]));
        System.out.println("a-b="+t7.subTwoNum(type[0], type[1]));
        System.out.println("a/b="+t7.divideTwoNum(type[0], type[1]));
      }
}


 
