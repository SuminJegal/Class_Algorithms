import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Created by jegalsumin on 2016. 9. 21..
 */
public class MergeSort {

    private int[] inputArray;

    private int countHowManyTwoWayMerge;
    private int countHowManyThreeWayMerge;

    public MergeSort(String fileName) throws IOException {

        countHowManyTwoWayMerge = 0;
        countHowManyThreeWayMerge = 0;

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

    public int[] twoWayMergeSorting(){
        return twoWayMergeSorting(inputArray);
    }

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
        countHowManyTwoWayMerge++;
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

    public int[] threeWayMergeSorting(){
        return threeWayMergeSorting(inputArray);
    }

    public int[] threeWayMergeSorting(int[] inputArray){
        if(inputArray.length == 1){
            return inputArray;
        }
        else if(inputArray.length == 2){
            int temp = inputArray[0];
            inputArray[0] = inputArray[1];
            inputArray[1] = inputArray[0];
            return inputArray;
        }
        else{
            int remainder = inputArray.length%3;
            int[] outputArray1 = null;
            int[] outputArray2 = null;
            int[] outputArray3 = null;
            switch (remainder){
                case 0:
                    outputArray1 = new int[(inputArray.length/3)];
                    outputArray2 = new int[(inputArray.length/3)];
                    outputArray3 = new int[(inputArray.length/3)];
                    System.arraycopy(inputArray,0, outputArray1,0,(inputArray.length/3));
                    System.arraycopy(inputArray,(inputArray.length/3), outputArray2,0,(inputArray.length/3));
                    System.arraycopy(inputArray,((inputArray.length/3)*2), outputArray3,0,(inputArray.length/3));
                    break;
                case 1:
                    outputArray1 = new int[(inputArray.length/3)];
                    outputArray2 = new int[(inputArray.length/3)];
                    outputArray3 = new int[((inputArray.length/3)+1)];
                    System.arraycopy(inputArray,0, outputArray1,0,(inputArray.length/3));
                    System.arraycopy(inputArray,(inputArray.length/3), outputArray2,0,(inputArray.length/3));
                    System.arraycopy(inputArray,((inputArray.length/3)*2), outputArray3,0,((inputArray.length/3)+1));
                    break;
                case 2:
                    outputArray1 = new int[(inputArray.length/3)];
                    outputArray2 = new int[((inputArray.length/3)+1)];
                    outputArray3 = new int[((inputArray.length/3)+1)];
                    System.arraycopy(inputArray,0, outputArray1,0,(inputArray.length/3));
                    System.arraycopy(inputArray,(inputArray.length/3), outputArray2,0,((inputArray.length/3)+1));
                    System.arraycopy(inputArray,(((inputArray.length/3)*2)+1), outputArray3,0,((inputArray.length/3)+1));
                    break;
            }
            outputArray1 = threeWayMergeSorting(outputArray1);
            outputArray2 = threeWayMergeSorting(outputArray2);
            outputArray3 = threeWayMergeSorting(outputArray3);
            return threeWayMerge(outputArray1,outputArray2,outputArray3);
        }
    }

    private int[] threeWayMerge(int[] inputArray1, int[] inputArray2, int[] inputArray3) {
        countHowManyThreeWayMerge++;
        int[] outputArray = new int[(inputArray1.length+inputArray2.length+inputArray3.length)];
        int indexOfArray1=0, indexOfArray2=0, indexOfArray3=0;
        int indexOfOutput=0;
        int countingDoneArray=0;
        while(countingDoneArray<20){
            if(countingDoneArray>10){
                switch (countingDoneArray) {
                    case 11:
                        if(inputArray2[indexOfArray2]<=inputArray3[indexOfArray3]){
                            outputArray[indexOfOutput] = inputArray2[indexOfArray2];
                            indexOfArray2++;
                            if(indexOfArray2==inputArray2.length) { countingDoneArray+=12; }
                        }
                        else {
                            outputArray[indexOfOutput] = inputArray3[indexOfArray3];
                            indexOfArray3++;
                            if(indexOfArray3==inputArray3.length) { countingDoneArray+=13; }
                        }
                        break;
                    case 12:
                        if(inputArray1[indexOfArray1]<=inputArray3[indexOfArray3]){
                            outputArray[indexOfOutput] = inputArray1[indexOfArray1];
                            indexOfArray1++;
                            if(indexOfArray1==inputArray1.length) { countingDoneArray+=11; }
                        }
                        else {
                            outputArray[indexOfOutput] = inputArray3[indexOfArray3];
                            indexOfArray3++;
                            if(indexOfArray3==inputArray3.length) { countingDoneArray+=13; }
                        }
                        break;
                    case 13:
                        if(inputArray1[indexOfArray1]<=inputArray2[indexOfArray2]){
                            outputArray[indexOfOutput] = inputArray1[indexOfArray1];
                            indexOfArray1++;
                            if(indexOfArray1==inputArray1.length) { countingDoneArray+=11; }
                        }
                        else {
                            outputArray[indexOfOutput] = inputArray2[indexOfArray2];
                            indexOfArray2++;
                            if(indexOfArray2==inputArray2.length) { countingDoneArray+=12; }
                        }
                        break;
                }
                indexOfOutput++;
            }
            else {
                switch (compareThreeValue(inputArray1[indexOfArray1],inputArray2[indexOfArray2], inputArray3[indexOfArray3])) {
                    case 1:
                        outputArray[indexOfOutput] = inputArray1[indexOfArray1];
                        indexOfArray1++;
                        if(indexOfArray1==inputArray1.length) { countingDoneArray+=11; }
                        break;
                    case 2:
                        outputArray[indexOfOutput] = inputArray2[indexOfArray2];
                        indexOfArray2++;
                        if(indexOfArray2==inputArray2.length) { countingDoneArray+=12; }
                        break;
                    case 3:
                        outputArray[indexOfOutput] = inputArray3[indexOfArray3];
                        indexOfArray3++;
                        if(indexOfArray3==inputArray3.length) { countingDoneArray+=13; }
                        break;
                }
                indexOfOutput++;
            }
        }
        if(countingDoneArray == 23){
            System.arraycopy(inputArray3,indexOfArray3,outputArray,indexOfOutput, inputArray3.length-indexOfArray3);
        }
        else if (countingDoneArray == 24){
            System.arraycopy(inputArray2,indexOfArray2,outputArray,indexOfOutput, inputArray2.length-indexOfArray2);
        }
        else {
            System.arraycopy(inputArray1,indexOfArray1,outputArray,indexOfOutput, inputArray1.length-indexOfArray1);
        }
        return outputArray;
    }

    private int compareThreeValue(int value1, int value2, int value3){
        int smallest;
        if(value1<=value2){
            if(value1<=value3){
                smallest = 1;
            }
            else{
                smallest = 3;
            }
        }
        else{
            if(value2<=value3){
                smallest = 2;
            }
            else{
                smallest = 3;
            }
        }
        return smallest;
    }

    public int getCountHowManyTwoWayMerge() {
        return countHowManyTwoWayMerge;
    }

    public int getCountHowManyThreeWayMerge() {
        return countHowManyThreeWayMerge;
    }
}
