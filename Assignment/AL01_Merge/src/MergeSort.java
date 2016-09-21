/**
 * Created by jegalsumin on 2016. 9. 21..
 */
public class MergeSort {

    public int[] twoWayMergeSorting(int[] inputArray){
        if(inputArray.length == 1){
            return inputArray;
        }
        else{
            int forAdd = 0;
            if((inputArray.length%2) != 0){
                forAdd = 1;
            }
            int[] outputArray1 = new int[(inputArray.length/2)];
            int[] outputArray2 = new int[((inputArray.length/2)+forAdd)];
            System.arraycopy(inputArray,0, outputArray1,0,inputArray.length/2);
            System.arraycopy(inputArray,(inputArray.length/2), outputArray2,0,((inputArray.length/2)+forAdd));
            outputArray1 = twoWayMergeSorting(outputArray1);
            outputArray2 = twoWayMergeSorting(outputArray2);
            return twoWayMerge(outputArray1,outputArray2);
        }
    }

    private int[] twoWayMerge(int[] inputArray1, int[] inputArray2){
        int[] outputArray = new int[(inputArray1.length+inputArray2.length)];
        int indexOfArray1=0, indexOfArray2=0;
        int indexOfOutput = 0;
        while((indexOfArray1<inputArray1.length) && (indexOfArray2<inputArray2.length)){
            if(inputArray1[indexOfArray1]<=inputArray2[indexOfArray2]){
                outputArray[indexOfOutput] = inputArray1[indexOfArray1];
                indexOfArray1++;
                indexOfOutput++;
            }
            else {
                outputArray[indexOfOutput] = inputArray2[indexOfArray2];
                indexOfArray2++;
                indexOfOutput++;
            }
        }
        if(indexOfArray1==inputArray1.length){
            System.arraycopy(inputArray2,indexOfArray2,outputArray,indexOfOutput, inputArray2.length-indexOfArray2);
        }
        else{
            System.arraycopy(inputArray1,indexOfArray1,outputArray,indexOfOutput, inputArray1.length-indexOfArray1);
        }
        return outputArray;
    }

    public int[] threeWayMergeSoring(int[] inputArray){
        return null;
    }
}
