import java.io.*;

/**
 * Created by jegalsumin on 2016. 9. 29..
 */
public class QuickSort {

    int[] inputArray;
    int inputArrayLength;

    public QuickSort(String fileName) throws IOException{
        inputArray = new int[10000000];
        inputArrayLength = 0;

        FileInputStream stream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(stream);
        StreamTokenizer token = new StreamTokenizer(reader);

        while(token.nextToken() != -1){
            switch (token.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    inputArray[inputArrayLength++] = (int) token.nval;
                    break;
                default:
                    break;
            }
        }
        stream.close();
    }

    /**
     *
     * change the value of array[i] and array[j]
     * @param i
     * @param j
     */
    private void swap(int i, int j){
        int temp = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = temp;
    }

    public int partition(int first, int last){
        int pivot = inputArray[last];
        int changingIndex = first-1;
        for(int sortingIndex = first; sortingIndex<last; sortingIndex++){
            if(inputArray[sortingIndex]<= pivot){
                changingIndex++;
                swap(changingIndex, sortingIndex);
            }
        }
        changingIndex++;
        swap(changingIndex,last);
        return changingIndex;
    }

    public int randomizedPartition(int first, int last){
        int i = randomChoice(first,last);
        swap(last, i);
        return partition(first, last);
    }

    private int randomChoice(int first, int last){
        int num1 = randomIndex(first,last);
        int num2 = randomIndex(first,last);
        int num3 = randomIndex(first,last);
        int bigger;
        int smaller;
        if(inputArray[num1]>=inputArray[num2]){
            bigger = num1;
            smaller = num2;
        }
        else{
            bigger = num2;
            smaller = num1;
        }
        if(inputArray[num3]>=inputArray[bigger]){
            return bigger;
        }
        else{
            if(inputArray[smaller]>=inputArray[num3]){
                return smaller;
            }
            else{
                return num3;
            }
        }

    }

    private int randomIndex(int first, int last){
        int ranIndex = last-(first-1);
        ranIndex = (int)(Math.random()*1000000) % ranIndex;
        return ranIndex + first;
    }

    public void quicksort(){
        quicksort(0,inputArrayLength);
    }

    public void quicksort(int p, int r){
        if(p<r){
            int q = partition(p,r);
            quicksort(p,q-1);
            quicksort(q+1,r);
        }
    }

    public void randomQuicksort(){
        randomQuicksort(0,inputArrayLength);
    }

    public void randomQuicksort(int p, int r){
        if(p<r){
            int q = randomizedPartition(p,r);
            randomQuicksort(p,q-1);
            randomQuicksort(q+1,r);
        }
    }

    /**
     * method for write output on file.
     * @param fileName
     * @throws IOException
     */
    public void writeOnFile(String fileName) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
        for(int i =0; i<inputArrayLength; i++){
            output.write(inputArray[i]+",");
        }
        output.close();
    }

    public static void main(String[] args) throws Exception{
        QuickSort q = new QuickSort("data04.txt");
        q.quicksort();
        q.writeOnFile("hw03_01_201202287_quick.txt");
        q.randomQuicksort();
        q.writeOnFile("hw03_01_201202287_quickRandom.txt");
    }

}
