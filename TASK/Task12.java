package TASK;

public class Task12 {
    public static void main(String[] args) {
        Point p=new Point(32, 32);
        for(int x=0;x<5;x++){
            p.scale();
            p.print();
        }
    }
    
}
class Point{
    private double x;
    private double y;

    public Point(double x, double y){
        this.x=x;
        this.y=y;
    }

    public void scale() {
        this.x/=2;
        this.y/=2;
    }

    public void print(){
        System.out.println("("+x+","+y+")");
    }

}
