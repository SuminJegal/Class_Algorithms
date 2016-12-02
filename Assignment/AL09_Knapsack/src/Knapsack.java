import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import com.sun.tools.javac.jvm.Items;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

/**
 * Created by jegalsumin on 2016. 11. 17..
 */
public class Knapsack {

    Thing[] things;
    int weightOfBag;
    ItemsThatNapSackHave[][] knapSack;

    public Knapsack(String fileName) throws IOException{

        things = new Thing[100];
        int whatIscurrentInput=1;
        int inputIndex=0;

        Scanner scan = new Scanner(System.in);

        FileInputStream stream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(stream);
        StreamTokenizer token = new StreamTokenizer(reader);


        while(token.nextToken() != -1){
            switch (token.ttype){
                case StreamTokenizer.TT_NUMBER:
                    switch (whatIscurrentInput){
                        case 1:
                            inputIndex = (int)token.nval;
                            things[inputIndex] = new Thing();
                            whatIscurrentInput = 2;
                            break;
                        case 2:
                            things[inputIndex].value = (int)token.nval;
                            whatIscurrentInput = 3;
                            break;
                        case 3:
                            things[inputIndex].weight = (int)token.nval;
                            whatIscurrentInput = 1;
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        stream.close();

        Thing[] temp = new Thing[inputIndex+1];
        System.arraycopy(things, 0, temp, 0, inputIndex+1);
        things = temp;

        System.out.print("How much does your bag weigh? (0-50) : ");
        weightOfBag = scan.nextInt();

        knapSack = new ItemsThatNapSackHave[inputIndex+1][weightOfBag+1];
        getOPTOfkNapSack(inputIndex,weightOfBag);
        printArrayKNapSack(inputIndex,weightOfBag);

//        for(int i=1; i<things.length; i++){
//            System.out.println(things[i]);
//        }
    }

    public void getOPTOfkNapSack(int inputIndex, int weightOfBag){
        for(int w=0; w<=weightOfBag; w++){
            knapSack[0][w] = new ItemsThatNapSackHave();
            knapSack[0][w].totalValue = 0;
        }
        for(int i=1; i<=inputIndex; i++){
            for(int w=0; w<=weightOfBag; w++){
                knapSack[i][w] = new ItemsThatNapSackHave();
                if(things[i].weight > w){
                    knapSack[i][w].totalValue = knapSack[i-1][w].totalValue;
                    knapSack[i][w].haveItems = knapSack[i-1][w].haveItems;
                }
                else {
                    int notAddCurrentValue = knapSack[i-1][w].totalValue;
                    int addCurrentValue = things[i].value + knapSack[i-1][w-things[i].weight].totalValue;
                    if(notAddCurrentValue>addCurrentValue){
                        knapSack[i][w].haveItems = knapSack[i-1][w].haveItems;
                        knapSack[i][w].totalValue = notAddCurrentValue;
                    }
                    else {
                        knapSack[i][w].haveItems = knapSack[i-1][w-things[i].weight].haveItems+" "+i;
                        knapSack[i][w].totalValue = addCurrentValue;
                    }
                }
            }
        }
    }

    public void printArrayKNapSack(int iNum, int wNum){
        for(int i=0; i<=iNum; i++){
            for(int j=0; j<=wNum; j++){
                System.out.printf("%4d",knapSack[i][j].totalValue);
            }
            System.out.println();
        }
        System.out.println("max : " + knapSack[iNum][wNum].totalValue);
        System.out.println("item : " + knapSack[iNum][wNum].haveItems);
    }

    class ItemsThatNapSackHave{
        int totalValue;
        String haveItems = "";
    }

    class Thing{
        int value;
        int weight;

        public String toString(){
            return "value : "+value+" weight: "+weight;
        }
    }

    public static void main(String[] args) throws IOException{

        new Knapsack("data11.txt");
    }
}
