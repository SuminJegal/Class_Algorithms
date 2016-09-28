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

    public void insert(String value, int key){
        for(int i=0; i<heap.node.length; i++){
            if(heap.node[i].getValue().equals(value)){
                System.out.println("aleady exist!");
                return;
            }
        }
        heap.insert(value, key);
        heap.afterChangeTheMiddleValue_heapify(heap.parent(heap.node.length-1));
    }

    public Node max(){
        return heap.node[0];
    }

    public void extract_max(){
        heap.swap(0, heap.node.length-1);
        heap.remove();
        heap.maxHeapify(0);
    }

    public void increase_key(String value, int key){
        int valueIndex=-1;
        for(int i=0; i<heap.node.length; i++){
            if(heap.node[i].getValue().equals(value)){
                valueIndex = i;
                break;
            }
        }
        if(valueIndex==-1){
            System.out.println("wrong input!");
            return;
        }
        heap.node[valueIndex].setKey(key);
        heap.afterChangeTheMiddleValue_heapify(valueIndex);

    }

    public void h_delete(String value){
        int valueIndex=-1;
        for(int i=0; i<heap.node.length; i++){
            if(heap.node[i].getValue().equals(value)){
                valueIndex = i;
                break;
            }
        }
        if(valueIndex==-1){
            System.out.println("wrong input!");
            return;
        }
        heap.swap(valueIndex,heap.node.length-1);
        heap.remove();
        heap.afterChangeTheMiddleValue_heapify(valueIndex);
    }



    class Heap {
        Node[] node;

        Heap(int length) {
            node = new Node[length];
        }

        void maxHeapify(int index){
            int largest;
            int left_child = this.leftChild(index);
            int right_child = this.rightChild(index);
            if(left_child<=(node.length-1) && node[left_child].getKey() > node[index].getKey()){
                largest = left_child;
            }
            else{
                largest = index;
            }
            if(right_child<=(node.length-1) && node[right_child].getKey() > node[largest].getKey()){
                largest = right_child;
            }
            if(largest != index){
                swap(index,largest);
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
            if(index%2==0){
                return (index/2)-1;
            }
            return index/2;
        }

        void buildMaxHeap(){
            for(int i=parent(node.length-1); i >= 0; i--){
                this.maxHeapify(i);
            }
        }

        void swap(int index1, int index2){
            Node temp = node[index1];
            node[index1] = node[index2];
            node[index2] = temp;
        }

        void remove(){
            Node[] newNodes = new Node[this.node.length-1];
            System.arraycopy(this.node,0,newNodes,0,this.node.length-1);
            this.node = newNodes;
        }

        void afterChangeTheMiddleValue_heapify(int index){
            for(int i = index; i>=0; i=parent(i)){
                this.maxHeapify(i);
            }
        }

        void insert(String value, int key){
            Node[] newNodes = new Node[this.node.length+1];
            System.arraycopy(this.node,0,newNodes,0,this.node.length);
            this.node = newNodes;
            node[node.length-1] = new Node(key, value);
        }
    }

    class Node {

        int key;
        String value;

        Node() {

        }

        Node(int key, String value){
            this.key = key;
            this.value = value;
        }

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
            String inputValue;
            int inputKey;
            switch (input){
                case 1:
                    System.out.print("추가시킬 과목 : ");
                    inputValue = scan.nextLine();
                    System.out.print("추가시킬 값 : ");
                    inputKey = scan.nextInt();
                    p.insert(inputValue, inputKey);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("max : " + p.max());
                    System.out.println();
                    break;
                case 3:
                    p.extract_max();
                    System.out.println();
                    break;
                case 4:
                    System.out.print("증가시킬 과목 : ");
                    inputValue = scan.nextLine();
                    System.out.print("증가시킬 값 : ");
                    inputKey = scan.nextInt();
                    p.increase_key(inputValue, inputKey);
                    System.out.println();
                    break;
                case 5:
                    System.out.print("제거시킬 과목 : ");
                    inputValue = scan.nextLine();
                    p.h_delete(inputValue);
                    System.out.println();
                    break;
                default:
                    break;
            }
            for(int i=0; i<p.heap.node.length; i++){
                System.out.println(p.heap.node[i]);
            }
            System.out.println("--------------------------------------");
            System.out.println("1.작업추가 2.최대값 3.최대값제거 4.원소키값증가 5.작업제거 6.종료");
            input = scan.nextInt();
            System.out.println("--------------------------------------");
            scan.nextLine();
        } while(input != 6);
    }


}
