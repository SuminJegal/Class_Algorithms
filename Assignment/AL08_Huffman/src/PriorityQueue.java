/**
 * Created by jegalsumin on 2016. 9. 25..
 */
public class PriorityQueue {

    Heap heap;

    public void insert(String value, int key){
        if(heap == null){
            heap = new Heap(1);
            heap.node[0] = new Tree(key,value);
            return;
        }
        for(int i=0; i<heap.node.length; i++){
            if(heap.node[i].getValue().equals(value)){
                heap.node[i].setKey(heap.node[i].getKey()+key);
                heap.afterChangeTheMiddlKey_heapify(i);
                return;
            }
        }
        heap.insert(value, key);
        heap.afterChangeTheMiddlKey_heapify(heap.parent(heap.node.length-1));
    }

    public void insert(Tree node){
        heap.insert(node);
        heap.afterChangeTheMiddlKey_heapify(heap.parent(heap.node.length-1));
    }

    public int size(){
        return heap.node.length;
    }

    public Tree min(){
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

    public Tree popMin(){
        if(this.size()==0){
            System.out.println("Nothing in Priority Queue");
            return null;
        }
        Tree returnValue = this.min();
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
        Tree[] node;

        Heap(int length) {
            node = new Tree[length];
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
            Tree temp = node[index1];
            node[index1] = node[index2];
            node[index2] = temp;
        }

        //method for remove the last node
        void remove(){
            Tree[] newNodes = new Tree[this.node.length-1];
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
            Tree[] newNodes = new Tree[this.node.length+1];
            System.arraycopy(this.node,0,newNodes,0,this.node.length);
            this.node = newNodes;
            node[node.length-1] = new Tree(key, value);
        }

        void insert(Tree newTree){
            Tree[] newNodes = new Tree[this.node.length+1];
            System.arraycopy(this.node,0,newNodes,0,this.node.length);
            this.node = newNodes;
            node[node.length-1] = newTree;
        }
    }

}
