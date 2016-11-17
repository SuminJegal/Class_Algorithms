import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by jegalsumin on 2016. 11. 11..
 */
public class Huffman {

    String inputCharacters = "";
    String inputData;

    public Tree makeHuffmanTreeInEncoding(String fileName)throws IOException{
        PriorityQueue priorityQueue = new PriorityQueue();
        FileInputStream stream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(stream);
        StreamTokenizer token = new StreamTokenizer(reader);

        while(token.nextToken() != -1){
            inputData = token.sval;
            for(int i=0; i<inputData.length(); i++){
                priorityQueue.insert(inputData.charAt(i)+"",1);
            }
        }
        stream.close();

        while(priorityQueue.size()>1){
            Tree newTree = new Tree();
            newTree.leftChild = priorityQueue.popMin();
            newTree.rightChild = priorityQueue.popMin();
            newTree.setKey(newTree.leftChild.getKey()+newTree.rightChild.getKey());
            priorityQueue.insert(newTree);
            if(newTree.leftChild.getValue() != null){
                inputCharacters = inputCharacters + newTree.leftChild.getValue();
            }
            if(newTree.rightChild.getValue() != null){
                inputCharacters = inputCharacters + newTree.rightChild.getValue();
            }
        }

        return priorityQueue.popMin();
    }

    public void encoding(String fileName) throws IOException {
        Tree huffmanTree = makeHuffmanTreeInEncoding(fileName);
        Node[] encoded = new Node[inputCharacters.length()];
        for(int i=0; i<encoded.length; i++){
            encoded[i] = new Node(inputCharacters.charAt(i));
        }
        Arrays.sort(encoded);
        for(int i=0; i<encoded.length; i++){
            encoded[i].code = setCodeByHuffman(encoded[i].alphabet,huffmanTree);
        }

        BufferedWriter output_encoded = new BufferedWriter(new FileWriter("hw08_01_201202287_encoded.txt"));
        for(int i =0; i<inputData.length(); i++){
            output_encoded.write(encoded[getIndexOfThisAlphabet(encoded,inputData.charAt(i))].code);
        }
        output_encoded.close();

        BufferedWriter output_table = new BufferedWriter(new FileWriter("hw08_01_201202287_table.txt"));
        for(int i =0; i<encoded.length; i++){
            output_table.write(encoded[i].alphabet+","+encoded[i].code+"\n");
        }
        output_table.close();
    }


    public void decoding(String fileName01, String fileName02) throws IOException{
        Node[] decodeTable = makeDecodedArray(fileName02);
        Tree decodingTree = makeHuffmanTreeInDecoding(decodeTable);
        String decoded = "";

        try{
            BufferedReader input = new BufferedReader(new FileReader(fileName01));
            String line = input.readLine();
            Tree tree;
            int tokenIndex = 0;
            while(line != null){
                StringTokenizer parser = new StringTokenizer(line);
                while (parser.hasMoreTokens()) {
                    String token = parser.nextToken();
                    while (tokenIndex < token.length()){
                        tree = decodingTree;
                        while (tree.leftChild!=null && tree.rightChild!=null){
                            if(token.charAt(tokenIndex) == '0'){
                                tree = tree.leftChild;
                                tokenIndex++;
                            }
                            else if(token.charAt(tokenIndex) == '1'){
                                tree = tree.rightChild;
                                tokenIndex++;
                            }
                        }
                        decoded = decoded + tree.getValue();
                    }
                }
                line = input.readLine();
            }
            input.close();
        } catch (IOException e){
            System.out.println(e);
        }

        BufferedWriter output_table = new BufferedWriter(new FileWriter("hw08_01_201202287_decoded.txt"));
        output_table.write(decoded);
        output_table.close();

    }

    private Tree makeHuffmanTreeInDecoding(Node[] decode){
        Tree tree = new Tree();
        Tree decodingTree;
        for(int i=0; i<decode.length; i++){
            decodingTree = tree;
            for(int j=0; j<decode[i].code.length(); j++){
                if(decode[i].code.charAt(j)=='0') {
                    if(decodingTree.leftChild == null){
                        decodingTree.leftChild = new Tree();
                    }
                    decodingTree = decodingTree.leftChild;
                }
                else {
                    if(decodingTree.rightChild == null){
                        decodingTree.rightChild = new Tree();
                    }
                    decodingTree = decodingTree.rightChild;
                }
                if(j==decode[i].code.length()-1){
                    decodingTree.setValue(decode[i].alphabet+"");
                }
            }
        }
        return tree;
    }

    public Node[] makeDecodedArray(String fileName)throws IOException{

        Node[] decoded = new Node[30];
        int decodeeIndex = 0;

        try{
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            String line = input.readLine();
            while(line != null){
                StringTokenizer parser = new StringTokenizer(line,",");
                while (parser.hasMoreTokens()) {
                    String token = parser.nextToken();
                    if(token.charAt(0) != '1' && token.charAt(0) != '0'){
                        decoded[decodeeIndex] = new Node(token.charAt(0));
                    }
                    else{
                        decoded[decodeeIndex++].code = token;
                    }
                }
                line = input.readLine();
            }
            input.close();
        } catch (IOException e){
            System.out.println(e);
        }

        Node[] newDecoded = new Node[decodeeIndex];
        System.arraycopy(decoded,0,newDecoded,0,decodeeIndex);

        /*for(int i=0; i<newDecoded.length; i++){
            System.out.println(newDecoded[i].alphabet+" "+newDecoded[i].code);
        }*/

        return newDecoded;
    }

    private String setCodeByHuffman(char input, Tree tree){
        if(tree.leftChild==null && tree.rightChild==null){
            if(tree.getValue().equals(input+"")){
                return "";
            }
            else
                return null;
        }
        String returnValue = setCodeByHuffman(input,tree.leftChild);
        if(returnValue != null){
            return "0"+returnValue;
        }
        returnValue = setCodeByHuffman(input,tree.rightChild);
        if(returnValue != null){
            return "1"+returnValue;
        }
        return null;
    }

    private int getIndexOfThisAlphabet(Node[] array, char alphabet){
        return (int)alphabet - (int)(array[0].alphabet);
    }

    class Node implements Comparable<Node>{
        char alphabet;
        String code="";

        Node(char alphabet){
            this.alphabet = alphabet;
        }

        @Override
        public int compareTo(Node node) {
            if((int)this.alphabet < (int)node.alphabet){
                return -1;
            }
            else if((int)this.alphabet == (int)node.alphabet){
                return 0;
            }
            else{
                return 1;
            }
        }

    }

}
