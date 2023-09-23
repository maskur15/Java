package Basic;

/**
 * Created by ASUS on 17-Sep-23.
 */
public class Operator {
    public static void main(String[] args) {
        //Bitwise operator section
        //modulus operator
        int x = 31, y = 12;
        System.out.println(x % y);
        //bitwise operator &-and, '|'-or, ^-xor >>-shift right
        int a = 12, b = 5;
        //bitwise and
        System.out.println("and " + (a & b));
        //bitwise or
        System.out.println("or: " + (a | b));
        //bitwise xor
        System.out.println("Xor " + (a ^ b));
        //rihgt shift -> MEANS divide the value by two for each right shift
        System.out.println("Right shift : " + (a >> 5));
        //left shift-> means multiply with two for each left shift
        System.out.println("Left shift " + (a << 3));
        // bitwise not - unary complement- changing all the individual bits
        System.out.println("Bitwise not" + (~a));

        /*Relational operator */
        //==, !=, >=, <=, > ,<
        //bollean logical operator
        //&-> and
        //short circuit logical and or && || ->its more perfect in boolean logic checking
        int d=0,e=12;
        if (d>0 && e%d>3) {
            System.out.println(a + "is greater than " + x + " " + y);
        }
        else {
            System.out.println("NOT grater ");
        }
        //Ternary operator
        System.out.println(a>b? "a is greater than b": "b is greater than b");

    }
}
