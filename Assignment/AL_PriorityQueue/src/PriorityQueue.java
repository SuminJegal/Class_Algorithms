import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Created by jegalsumin on 2016. 9. 25..
 */
public class PriorityQueue {

    Heap heap;
    int heapLength = 0;

    public PriorityQueue(String fileName) throws IOException {

        int arraytempArrayIndex = -1;
        Node[] tempArray = new Node[20];

        FileInputStream stream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(stream);
        StreamTokenizer token = new StreamTokenizer(reader);

        while (token.nextToken() != -1) {
            switch (token.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    arraytempArrayIndex++;
                    tempArray[arraytempArrayIndex] = new Node();
                    tempArray[arraytempArrayIndex].setKey((int)token.nval);
                    break;
                case StreamTokenizer.TT_WORD:
                    tempArray[arraytempArrayIndex].setValue(token.sval);
                    break;
                default:
                    break;
            }
        }
        heapLength = arraytempArrayIndex+1;
        stream.close();

        heap = new Heap(heapLength);
        for(int i = 0; tempArray[i] != null; i++){
            heap.node[i] = tempArray[i];
            System.out.println("key: "+ heap.node[i].getKey() + ", value : "+heap.node[i].getValue());
        }

        heap.buildMaxHeap();
        heap.maxHeapify();

    }

    public void insert(Node x){

    }

    public String max(){
        return null;
    }

    public void extract_max(){

    }

    public void increase_key(String value, int key){

    }

    public void h_delete(String x){
        
    }



    class Heap {
        Node[] node;

        Heap(int length) {
            node = new Node[length];
        }

        private void maxHeapify(){

        }

        private void buildMaxHeap(){

        }
    }

    class Node {

        int key;
        String value;

        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            if(this.value == null){
                this.value = value;
            }
            else {
                this.value = this.value + " " + value;
            }
        }
        public int getKey() {
            return key;
        }
        public void setKey(int key) {
            this.key = key;
        }
    }

    public static void main (String[] args) throws IOException{
        PriorityQueue p = new PriorityQueue("data03.txt");
    }


}
