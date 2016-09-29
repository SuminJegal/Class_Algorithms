import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Created by jegalsumin on 2016. 9. 29..
 */
public class QuickSort {

    int[] inputArray;

    public QuickSort(String fileName) throws IOException{
        inputArray = new int[10000000];
        int inputArrayLength = 0;

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

    
}
