import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by jegalsumin on 2016. 9. 8..
 */
public class InsertionSort {

    int[] inputArray;

    public InsertionSort(String fileName) throws IOException{

        int[] tempArray = new int[10000000];
        int arrayIndex = 0;

        FileInputStream stream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(stream);
        StreamTokenizer token = new StreamTokenizer(reader);

        while(true){
            while(token.nextToken() != -1){
                switch (token.ttype){
                    case StreamTokenizer.TT_NUMBER:
                        tempArray[arrayIndex++] = (int)token.nval;
                        break;
                    default:
                        break;
                }
            }
            stream.close();
            inputArray = new int[arrayIndex];
            System.arraycopy(tempArray,0,inputArray,0,arrayIndex);
            return;
        }
    }

    public int[] insertionSorting(){
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

    public int[] binaryInsertionSorting(){
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
    
}

