import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

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
        }

        heap.buildMaxHeap();

    }

    public void insert(Node x){

    }

    public Node max(){
        return heap.node[0];
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

        private void maxHeapify(int index){
            int largest;
            int left_child = this.leftChild(index);
            int right_child = this.rightChild(index);
            if(left_child<=(node.length-1) && node[left_child].getKey() > node[node.length-1].getKey()){
                largest = left_child;
            }
            else{
                largest = index;
            }
            if(right_child<=(node.length-1) && node[right_child].getKey() > node[largest].getKey()){
                largest = right_child;
            }
            if(largest != index){
                Node temp = node[index];
                node[index] = node[largest];
                node[largest] = temp;
                maxHeapify(largest);
            }
        }

        int leftChild(int index){
            return (index+1)*2-1;
        }

        int rightChild(int index){
            return (index+1)*2;
        }

        int parent(int index){
            return (index-1)/2;
        }

        private void buildMaxHeap(){
            for(int i=parent(node.length-1); i > 0; i--){
                this.maxHeapify(i);
            }
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

        public String toString(){
            return key +", "+ value;
        }
    }

    public static void main (String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        PriorityQueue p = new PriorityQueue("data03.txt");
        int input=0;
        do{
            switch (input){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
            for(int i=0; i<p.heap.node.length; i++){
                System.out.println(p.heap.node[i]);
            }
            System.out.println("--------------------------------------");
            System.out.println("1.작업추가 2.최대값 3.최대값제거 4.원소키값증가 5.작업제거 6.종료");
            input = scan.nextInt();
        } while(input == 6);
    }


}
