import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Created by jegalsumin on 2016. 11. 11..
 */
public class Huffman {


    Huffman(String fileName)throws IOException{

        FileInputStream stream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(stream);
        StreamTokenizer token = new StreamTokenizer(reader);

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

    }
}
