package Basic;

/**
 * Created by ASUS on 17-Sep-23.
 */
public class Array {

    public static void main(String[] args) {
        int arr[] = new int[10];
        for(int i=0;i<10;i++){
            arr[i]=i+1;
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        int matrix[][]= new int[5][5];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++)
            {
                matrix[i][j]=i+j;
            }
        }
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[i].length;j++)
            {
                System.out.print(matrix[i][j]+"  ");
            }
            System.out.println();
        }
    }
}
