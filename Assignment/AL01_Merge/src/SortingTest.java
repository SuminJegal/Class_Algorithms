import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by jegalsumin on 2016. 9. 22..
 */
public class SortingTest {

    public static void main(String[] args) throws IOException{
        MergeSort instance_merge = new MergeSort("data02.txt");
        int[] resurt_2_merge = instance_merge.twoWayMergeSorting();

        BufferedWriter output_2_merge= new BufferedWriter(new FileWriter("hw02_01_201202287_merge.txt"));
        for(int i =0; i<resurt_2_merge.length; i++){
            output_2_merge.write(resurt_2_merge[i]+",");
        }
        output_2_merge.newLine();
        output_2_merge.write("How many call the method 'merge'"+instance_merge.getCountHowManyTwoWayMerge());
        output_2_merge.close();

        int[] resurt_3_merge = instance_merge.threeWayMergeSorting();

        BufferedWriter output_3_merge= new BufferedWriter(new FileWriter("hw02_01_201202287_3way_merge.txt"));
        for(int i =0; i<resurt_3_merge.length; i++){
            output_3_merge.write(resurt_3_merge[i]+",");
        }
        output_3_merge.newLine();
        output_3_merge.write("How many call the method 'merge'"+instance_merge.getCountHowManyThreeWayMerge());
        output_3_merge.close();

    }
}
