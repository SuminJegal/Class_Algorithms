/**
 * Created by jegalsumin on 2016. 10. 25..
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Closest {

    Pair[] pairs;

    public Closest(String fileName) throws IOException {

        double[] tempArray = new double[100000];
        int arrayIndex = 0;

        FileInputStream stream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(stream);
        StreamTokenizer token = new StreamTokenizer(reader);


        while(token.nextToken() != -1){
            switch (token.ttype){
                case StreamTokenizer.TT_NUMBER:tempArray[arrayIndex++] = token.nval;
                    break;
                default:
                    break;
            }
        }
        stream.close();
        pairs = new Pair[arrayIndex/2];
        for(int i=0; i<arrayIndex; i += 2){
            pairs[i/2] = new Pair(tempArray[i],tempArray[i+1]);
        }

    }

    public double findClosestPair(){
        return findClosestPair(pairs);
    }

    public double findClosestPair(Pair[] input){
        Arrays.sort(input);
        if(input.length <= 3){
            return bruteForce(input);
        }
        int forAdd = 0;
        if((input.length%2) != 0){
            forAdd = 1;
        }
        Pair[] zone1 = new Pair[input.length/2];
        Pair[] zone2 = new Pair[input.length/2+forAdd];
        System.arraycopy(input,0,zone1,0,input.length/2);
        System.arraycopy(input,(input.length/2), zone2,0,((input.length/2)+forAdd));
        double delta1 = findClosestPair(zone1);
        double delta2 = findClosestPair(zone2);
        double delta = (delta1<=delta2)? delta1:delta2;

        Pair[] tempWindow = new Pair[input.length];
        int windowIndex=0;
        for(int i=0; i<input.length; i++){
            if(Math.abs(input[i].x-input[input.length/2].x)<=delta){
                tempWindow[windowIndex++] = input[i];
            }
        }
        Pair[] window = new Pair[windowIndex];
        System.arraycopy(tempWindow,0,window,0,windowIndex);
        window = sortingByY(window);
        for(int i =0; i<window.length-1; i++){
            int j=1;
            while((window[i+j].y-window[i].y)<=delta){
                double tempDistance = getDistance(window[i+j],window[i]);
                if(tempDistance <= delta){
                    delta = tempDistance;
                }
                j++;
            }
        }
        return delta;
    }

    public double bruteForce(Pair[] input){
        double closest = 100000000;
        for(int i=0; i<input.length; i++){
            for(int j=i+1; j<input.length; j++){
                double tempDistance = getDistance(input[i],input[j]);
                if(tempDistance<closest){
                    closest = tempDistance;
                }
            }
        }
        return closest;
    }

    public double getDistance(Pair input1, Pair input2){
        return Math.sqrt(((input1.x-input2.x)*(input1.x-input2.x))+((input1.y-input2.y)*(input1.y-input2.y)));
    }

    public Pair[] sortingByY(Pair[] input){
        for (int i=input.length-1; i>0; i--) {
            for (int j=0; j<i; j++) {
                if (input[j].y > input[j+1].y) {
                    Pair temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
        }
        return input;
    }

    class Pair implements Comparable<Pair>{
        double x;
        double y;

        Pair(double x, double y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair pair){
            if(this.x < pair.x){
                return -1;
            }
            else if(this.x == pair.x){
                return 0;
            }
            else{
                return 1;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Closest c = new Closest("data07_closest.txt");
        System.out.println(c.findClosestPair());
    }
}
