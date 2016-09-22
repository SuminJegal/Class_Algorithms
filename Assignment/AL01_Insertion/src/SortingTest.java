import java.io.*;

/**
 * Created by jegalsumin on 2016. 9. 21..
 */
public class SortingTest {

    public static void main(String[] args) throws IOException {

        long startTime,endTime;

        InsertionSort instanceForSorting = new InsertionSort("data02.txt");
        InsertionSort instance_10 = new InsertionSort("hw02_10man.txt");
        InsertionSort instance_100 = new InsertionSort("hw02_100man.txt");

        System.out.println("\n<Insertion Sort>");
        startTime = System.nanoTime();
        int[] resurt_insertion = instanceForSorting.insertionSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

        System.out.println("\n<Binary insertion Sort>");
        startTime = System.nanoTime();
        int[] resurt_binaryInsertion = instanceForSorting.binaryInsertionSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

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

        System.out.println("\n<10 Insertion Sort>");
        startTime = System.nanoTime();
        instance_10.insertionSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

        System.out.println("\n<10 Binary insertion Sort>");
        startTime = System.nanoTime();
        instance_10.binaryInsertionSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

        System.out.println("\n<100 Insertion Sort>");
        startTime = System.nanoTime();
        instance_100.insertionSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

        System.out.println("\n<100 Binary insertion Sort>");
        startTime = System.nanoTime();
        instance_100.binaryInsertionSorting();
        endTime = System.nanoTime();
        System.out.println("start nano time : "+startTime);
        System.out.println("end nano time   : "+endTime);
        System.out.println("--------------------------------------");
        System.out.println("total nano time : "+(endTime-startTime));

    }
}
