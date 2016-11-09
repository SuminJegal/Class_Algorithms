/**
 * Created by jegalsumin on 2016. 9. 25..
 */
public class PriorityQueue {

    Heap heap;

    public void insert(String value, int key){
        if(heap == null){
            heap = new Heap(1);
            heap.node[0] = new Node(key,value);
            return;
        }
        for(int i=0; i<heap.node.length; i++){
            if(heap.node[i].getValue().equals(value)){
                System.out.println("aleady exist!");
                return;
            }
        }
        heap.insert(value, key);
        heap.afterChangeTheMiddlKey_heapify(heap.parent(heap.node.length-1));
    }

    public int size(){
        return heap.node.length;
    }

    public Node min(){
        return heap.node[0];
    }

    public void extract_min(){
        if(this.size()==0){
            return;
        }
        heap.swap(0, heap.node.length-1);
        heap.remove();
        heap.minHeapify(0);
    }

    public Node popMin(){
        if(this.size()==0){
            System.out.println("Nothing in Priority Queue");
            return null;
        }
        Node returnValue = this.min();
        this.extract_min();
        return returnValue;
    }

    public void modifyKey(String value, int afterKey){
        delete(value);
        insert(value,afterKey);
    }

    public void delete(String value){
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
        heap.afterChangeTheMiddlKey_heapify(valueIndex);
    }



    class Heap {
        Node[] node;

        Heap(int length) {
            node = new Node[length];
        }

        void minHeapify(int index){
            int smallest;
            int left_child = this.leftChild(index);
            int right_child = this.rightChild(index);
            if(left_child<=(node.length-1) && node[left_child].getKey() < node[index].getKey()){
                smallest = left_child;
            }
            else{
                smallest = index;
            }
            if(right_child<=(node.length-1) && node[right_child].getKey() < node[smallest].getKey()){
                smallest = right_child;
            }
            if(smallest != index){
                swap(index,smallest);
                minHeapify(smallest);
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

        void buildMinHeap(){
            for(int i=parent(node.length-1); i >= 0; i--){
                this.minHeapify(i);
            }
        }

        //method for swap
        void swap(int index1, int index2){
            Node temp = node[index1];
            node[index1] = node[index2];
            node[index2] = temp;
        }

        //method for remove the last node
        void remove(){
            Node[] newNodes = new Node[this.node.length-1];
            System.arraycopy(this.node,0,newNodes,0,this.node.length-1);
            this.node = newNodes;
        }

        //method for heap if the key in the heap was changed
        void afterChangeTheMiddlKey_heapify(int index){
            for(int i = index; i>=0; i=parent(i)){
                this.minHeapify(i);
            }
        }

        //method for insert
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

}
