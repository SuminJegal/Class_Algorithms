import java.io.IOException;
import java.util.*;

/**
 * Created by jegalsumin on 2016. 9. 8..
 */
public class InsertionSort {

    //public InsertionSort() throws IOException{




    //}

    public int[] insertionSorting(int[] inputArray){
        int[] outputArray = new int[inputArray.length];
        outputArray[0] = inputArray[0];
        for(int inputArrayIndexforSorting=1; inputArrayIndexforSorting<inputArray.length; inputArrayIndexforSorting++){
            int key = inputArray[inputArrayIndexforSorting];
            int outputArrayIndexforSorting = inputArrayIndexforSorting-1;
            while(outputArrayIndexforSorting>=0 && key<outputArray[outputArrayIndexforSorting]){
                outputArray[outputArrayIndexforSorting+1] = outputArray[outputArrayIndexforSorting];
                outputArrayIndexforSorting--;
            }
            outputArray[outputArrayIndexforSorting+1] = key;
        }
        return outputArray;

    }
    
}
