/**
 * Created by jegalsumin on 2016. 9. 22..
 */
public class SortingTest {

    public static void main(String[] args){
        MergeSort m = new MergeSort();
        int[] input2 = {5, 2, 4, 6, 3, 4, 76, 11, 234, 53, 1, -1, 2, 3, 9, 8, 1, 7};
        int[] expected2 = m.twoWayMergeSorting(input2);
        System.out.print("merge sort : ");
        for(int k =0; k<expected2.length; k++){
            System.out.print(expected2[k]+" ");
        }
        System.out.println();

    }
}