import java.io.IOException;

/**
 * Created by jegalsumin on 2016. 11. 17..
 */
public class Main {

    public static void main(String[] args) throws IOException{
        Huffman h = new Huffman();
        h.encoding("data10.txt");
        h.decoding("data10_encoded.txt","data10_table.txt");
        //h.makeDecodedArray("data10_table.txt");
    }
}
