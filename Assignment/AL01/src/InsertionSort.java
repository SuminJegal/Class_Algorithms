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

    public int[] binaryInsertionSorting(int[] inputArray){
        int[] outputArray = new int[inputArray.length];
        outputArray[0] = inputArray[0];
        for(int inputArrayIndexforSorting=1; inputArrayIndexforSorting<inputArray.length; inputArrayIndexforSorting++){
            int key = inputArray[inputArrayIndexforSorting];
            int lastSortedIndexInOutputArray = inputArrayIndexforSorting-1;
            if(outputArray[0]>key){
                System.arraycopy(outputArray,0,outputArray,1,lastSortedIndexInOutputArray+1);
                outputArray[0] = key;
            }
            else if(key>=outputArray[0] && key<outputArray[lastSortedIndexInOutputArray]){
                int indexOfFrontOfKey = binarySearch(outputArray,key,0,lastSortedIndexInOutputArray);
                System.arraycopy(outputArray,indexOfFrontOfKey+1,outputArray,indexOfFrontOfKey+2,lastSortedIndexInOutputArray-indexOfFrontOfKey);
                outputArray[indexOfFrontOfKey+1] = key;
            }
            else{
                outputArray[lastSortedIndexInOutputArray+1] = key;
            }
        }
        return outputArray;

    }

    private int binarySearch (int sortedArray[], int key, int startIndex, int endIndex)
    {
        if((startIndex+1)==endIndex){
            return startIndex;
        }

        int indexSearch = startIndex + ((endIndex - startIndex) / 2);

        if(key<sortedArray[indexSearch]){
            return binarySearch(sortedArray,key,startIndex,indexSearch);
        }
        else if(key>sortedArray[indexSearch]){
            return binarySearch(sortedArray,key,indexSearch,endIndex);
        }
        else{
            return indexSearch;
        }

    }

    public static void main(String[] args){
        InsertionSort i = new InsertionSort();
        int[] input = {5, 2, 4, 6, 2, 3, 9, 8, 1, 7};
        int[] expected = i.binaryInsertionSorting(input);
        for(int k =0; k<expected.length; k++){
            System.out.println(expected[k]);
        }
    }
    
}

