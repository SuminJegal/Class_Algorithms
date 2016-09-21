/**
 * Created by jegalsumin on 2016. 9. 21..
 */
public class SortingTest {

    public static void main(String[] args){
        InsertionSort i = new InsertionSort();
        int[] input = {5, 2, 4, 6, 2, 3, 9, 8, 1, 7};
        int[] expected = i.binaryInsertionSorting(input);
        System.out.print("insertion sort binary : ");
        for(int k =0; k<expected.length; k++){
            System.out.print(expected[k]+" ");
        }
        System.out.println();


    }
}
