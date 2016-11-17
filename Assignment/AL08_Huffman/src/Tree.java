/**
 * Created by jegalsumin on 2016. 11. 17..
 */
public class Tree {

    int key;
    String value;
    Tree leftChild;
    Tree rightChild;

    Tree(){
    }

    Tree(int key, String value){
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
        return key +", "+ value +"\n l: " + leftChild +", r: "+rightChild;
    }
}
