package Basic.oop;

/**
 * Created by ASUS on 17-Sep-23.
 */
class  Box{
    double height ,width,length;
    Box(double height,double width, double length){
        this.length=length;
        this.height=height;
        this.width=width;
    }
    void volume(){
        double volume = this.width*this.height*this.length;
        System.out.println("VOluem is : "+volume);
    }
    @Override
    public String toString() {
        return this.length+" "+this.width+" "+this.height;
    }
}
public class BoxDemo {
    public static void main(String[] args) {
        Box b1 = new Box(2,2,2);
        b1.height=2.0;
        b1.width=4.9;
        b1.length=9;
        double b1_volume = b1.height*b1.width*b1.length;
        System.out.println(b1);
        Box b2 = new Box(2,2,4);
        b2.length=2;
        b2.width=3.3;
        b2.height=2.2;
        double b2_volume= b2.height*b2.width*b2.length;
        System.out.println(b1_volume+" "+b2_volume);
        b1.volume();
        b2.volume();

    }
}
