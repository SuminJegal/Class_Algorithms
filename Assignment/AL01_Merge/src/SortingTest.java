import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by jegalsumin on 2016. 9. 22..
 */
public class SortingTest {

    public static void main(String[] args) throws IOException{

        long startTime, endTime;

        MergeSort instance_merge = new MergeSort("data02.txt");

        System.out.println("\n<Merge Sort>");
        startTime = System.nanoTime();
        int[] resurt_2_merge = instance_merge.twoWayMergeSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

        BufferedWriter output_2_merge= new BufferedWriter(new FileWriter("hw02_01_201202287_merge.txt"));
        for(int i =0; i<resurt_2_merge.length; i++){
            output_2_merge.write(resurt_2_merge[i]+",");
        }
        output_2_merge.newLine();
        output_2_merge.write("How many call the method 'merge'"+instance_merge.getCountHowManyTwoWayMerge());
        output_2_merge.close();


        System.out.println("\n<3-way Merge Sort>");
        startTime = System.nanoTime();
        int[] resurt_3_merge = instance_merge.threeWayMergeSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

        BufferedWriter output_3_merge= new BufferedWriter(new FileWriter("hw02_01_201202287_3way_merge.txt"));
        for(int i =0; i<resurt_3_merge.length; i++){
            output_3_merge.write(resurt_3_merge[i]+",");
        }
        output_3_merge.newLine();
        output_3_merge.write("How many call the method 'merge'"+instance_merge.getCountHowManyThreeWayMerge());
        output_3_merge.close();




        MergeSort instance_10 = new MergeSort("hw02_10man.txt");

        System.out.println("\n<10man Merge Sort>");
        startTime = System.nanoTime();
        instance_10.twoWayMergeSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

        System.out.println("\n<10 man 3-way Merge Sort>");
        startTime = System.nanoTime();
        instance_10.threeWayMergeSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));


        MergeSort instance_100 = new MergeSort("hw02_100man.txt");

        System.out.println("\n<100man Merge Sort>");
        startTime = System.nanoTime();
        instance_100.twoWayMergeSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

        System.out.println("\n<100 man 3-way Merge Sort>");
        startTime = System.nanoTime();
        instance_100.threeWayMergeSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));





    }
}
