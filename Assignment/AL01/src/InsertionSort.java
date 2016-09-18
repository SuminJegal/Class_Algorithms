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
        /*int[] outputArray = new int[inputArray.length];
        boolean outputArrayCheck;
        outputArray[0] = inputArray[0];*/

        int[] outputArray = new int[inputArray.length];
        boolean[] outputArrayCheck;
        outputArray[0] = inputArray[0];
        for(int inputArrayIndexforSorting=1; inputArrayIndexforSorting<inputArray.length; inputArrayIndexforSorting++){
            int key = inputArray[inputArrayIndexforSorting];
            if(key<outputArray[0]){
                for(int outputIndexforCopy = inputArrayIndexforSorting; outputIndexforCopy>0; outputIndexforCopy--){
                    outputArray[outputIndexforCopy] = outputArray[outputIndexforCopy-1];
                }
                outputArray[0] = key;
            }
            else if(key>=outputArray[inputArrayIndexforSorting-1]){
                outputArray[inputArrayIndexforSorting-1] = key;
            }
            else{
                outputArrayCheck = new boolean[inputArray.length];
                int binarytimes = 2;
                int outputArrayIndexforSorting = inputArrayIndexforSorting/binarytimes;
                while(outputArrayIndexforSorting>=0 && outputArray[outputArrayIndexforSorting]!=key){
                    if(outputArrayCheck[outputArrayIndexforSorting]==true){
                        break;
                    }
                    outputArrayCheck[outputArrayIndexforSorting] = true;
                    binarytimes *= 2;
                    if(key<outputArray[outputArrayIndexforSorting]) {
                        outputArrayIndexforSorting =
                                outputArrayIndexforSorting - (inputArrayIndexforSorting / binarytimes);
                    }
                    else {
                        outputArrayIndexforSorting =
                                outputArrayIndexforSorting + (inputArrayIndexforSorting / binarytimes);
                    }
                }
                //outputArrayIndexforSorting += 1;
                //if(outputArrayIndexforSorting<0){
                //    outputArrayIndexforSorting = 0;
                //}
                for(int outputIndexforCopy = inputArrayIndexforSorting; outputIndexforCopy>outputArrayIndexforSorting; outputIndexforCopy--){
                    outputArray[outputIndexforCopy] = outputArray[outputIndexforCopy-1];
                }
                outputArray[outputArrayIndexforSorting] = key;
            }
        }
        return outputArray;

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

