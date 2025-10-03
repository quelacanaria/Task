public class f001_Primitive {

    public static void main(String[] args) {
        // Primitive = simple value stored directly in memory(stack)
        // Reference = memory address (stack) that points to the (heap)

        // Primitive-int, double, char, boolean, short, long, float
        // Reference-String, array, object 

        // creating a variable

        // 1. datatype - int, double, long, char, boolean, short, String,  array, object, float
        // 2. variable declaration - anything in abc
        // 3. assignment - anything as long as it fits the datatype

        int grade = 90;
        int topNumber = 1;
        int numberStudents = 50;
        double price = 42_000_000_000.00;
        char remarks = 'A';
        boolean pass = true;
        String name = "Sarah Discaya";
        String luxuryCar = "Bently Bentayga";

        System.out.println(name + "is a former top" + topNumber + "students in their classroom over "
                + numberStudents + "students and her grade is " + remarks + "+ "
                + grade + "and because of that her dad gave her a $" + price
                + "to buy her favorite car " + luxuryCar);


    }

}
