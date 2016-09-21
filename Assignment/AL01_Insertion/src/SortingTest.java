import java.io.IOException;

/**
 * Created by jegalsumin on 2016. 9. 21..
 */
public class SortingTest {

    public static void main(String[] args) throws IOException {
        InsertionSort test01 = new InsertionSort("data02.txt");
        int[] expected = test01.insertionSorting();
        System.out.print("insertion sort : ");
        for(int k =0; k<expected.length; k++){
            System.out.print(expected[k]+" ");
        }
        System.out.println();


    }
}
