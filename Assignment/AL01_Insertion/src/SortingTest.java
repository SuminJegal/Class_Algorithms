import java.io.*;

/**
 * Created by jegalsumin on 2016. 9. 21..
 */
public class SortingTest {

    public static void main(String[] args) throws IOException {

        InsertionSort instanceForSorting = new InsertionSort("data02.txt");
        int[] resurt_insertion = instanceForSorting.insertionSorting();
        int[] resurt_binaryInsertion = instanceForSorting.insertionSorting();

        BufferedWriter output_insertion = new BufferedWriter(new FileWriter("hw02_01_201202287_insertion.txt"));
        for(int i =0; i<resurt_insertion.length; i++){
            output_insertion.write(resurt_insertion[i]+",");
        }
        output_insertion.close();

        BufferedWriter output_binaryInsertion = new BufferedWriter(new FileWriter("hw02_01_201202287_binary_insertion.txt"));
        for(int i =0; i<resurt_binaryInsertion.length; i++){
            output_binaryInsertion.write(resurt_binaryInsertion[i]+",");
        }
        output_binaryInsertion.close();

    }
}
