package Basic;

/**
 * Created by ASUS on 16-Sep-23.
 */
public class UnaryOperator
{
    public static void main(String[] args) {
        int a=10;
        int b=10;
        int c=2;
        System.out.println(a++ + ++a);
        System.out.print(b++ + b++);
        if(a==b){
            System.out.println("Equal ");
        }
        else{
            System.out.println("Not equal");
        }
        System.out.println(a>b?" a is greater":"b is greater");
    }
}
