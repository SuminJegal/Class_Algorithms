/**
 * Created by jegalsumin on 2016. 10. 20..
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;


public class Inversion {

    int[] inputArray;

    public Inversion(String fileName) throws IOException {

        int[] tempArray = new int[100000];
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

    public ICountAndList sortAndCount(){
        return sortAndCount(inputArray);
    }

    public ICountAndList sortAndCount(int[] inputArray){
        if(inputArray.length == 1){
            return new ICountAndList(0,inputArray);
        }
        else{
            int forAdd = 0;
            if((inputArray.length%2) != 0){
                forAdd = 1;
            }
            int[] tempArray1 = new int[(inputArray.length/2)];
            int[] tempArray2 = new int[((inputArray.length/2)+forAdd)];
            System.arraycopy(inputArray,0, tempArray1,0,inputArray.length/2);
            System.arraycopy(inputArray,(inputArray.length/2), tempArray2,0,((inputArray.length/2)+forAdd));
            ICountAndList output1 = sortAndCount(tempArray1);
            ICountAndList output2 = sortAndCount(tempArray2);
            ICountAndList realOutput = mergeAndCount(output1.list,output2.list);
            realOutput.inversionCount = realOutput.inversionCount+output1.inversionCount+output2.inversionCount;
            return realOutput;
        }
    }

    public ICountAndList mergeAndCount(int[] inputArray1, int[] inputArray2){
        int inversionCount = 0;
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
                inversionCount = inversionCount+(inputArray1.length-indexOfArray1);
            }
        }
        if(indexOfArray1==inputArray1.length){
            System.arraycopy(inputArray2,indexOfArray2,outputArray,indexOfOutput, inputArray2.length-indexOfArray2);
        }
        else{
            System.arraycopy(inputArray1,indexOfArray1,outputArray,indexOfOutput, inputArray1.length-indexOfArray1);
        }
        return new ICountAndList(inversionCount,outputArray);
    }

    class ICountAndList {
        int inversionCount;
        int[] list;

        ICountAndList(int inversionCount, int[] list){
            this.inversionCount = inversionCount;
            this.list = list;
        }
    }

    public static void main(String[] args)throws IOException{
        Inversion i = new Inversion("data07_inversion.txt");
        System.out.println(i.sortAndCount().inversionCount);
    }
}
