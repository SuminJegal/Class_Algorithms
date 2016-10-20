/**
 * Created by jegalsumin on 2016. 10. 20..
 */
import java.util.Scanner;

public class SkyLine {

    int numberOfBuilding;
    Building[] buildings;

    public SkyLine(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Number of Building : ");
        numberOfBuilding = scan.nextInt();
        scan.nextLine();
        buildings = new Building[numberOfBuilding];
        String input;
        for(int i = 0; i < numberOfBuilding; i++){
            System.out.print((i+1)+" building : ");
            input = scan.nextLine();
            String[] str = new String(input).split(",");
            buildings[i] = new Building();
            buildings[i].left = Integer.parseInt(str[0]);
            buildings[i].height = Integer.parseInt(str[1]);
            buildings[i].right = Integer.parseInt(str[2]);
        }
        int[] result = findSkyLine(buildings, 1, numberOfBuilding);
        for(int i=1;i<result.length;i++){
            System.out.print(result[i]+", ");
        }
    }

    public int[] findSkyLine(Building[] buildings, int start, int end){
        if(start==end){
            int[] skyLine = new int[1];
            skyLine = appendString(skyLine, buildings[start-1].left,buildings[end-1].height);
            skyLine = appendString(skyLine, buildings[end-1].right,0);
            return skyLine;
        }
        int midium = (start + end)/2;
        int[] skyLine1 = findSkyLine(buildings, start, midium);
        int[] skyLine2 = findSkyLine(buildings, midium+1, end);
        return mergeSkyLine(skyLine1, skyLine2);
    }

    public int[] mergeSkyLine(int[] skyLine1, int[] skyLine2){
        int[] newSkyLine = new int[1];
        newSkyLine[0] = 0;
        int currentX;
        int maxHeight;
        int currentHeight1 = 0;
        int currentHeight2 = 0;
        int skyLineCount1 = skyLine1.length/2;
        int skyLineCount2 = skyLine2.length/2;
        int skyLineNowPoint1 = 1;
        int skyLineNowPoint2 = 1;
        while (skyLineCount1>0 &&skyLineCount2>0){
            if(skyLine1[skyLineNowPoint1]<skyLine2[skyLineNowPoint2]){
                currentX = skyLine1[skyLineNowPoint1];
                currentHeight1 = skyLine1[skyLineNowPoint1+1];
                maxHeight = currentHeight1;
                if(currentHeight2 > maxHeight){
                    maxHeight = currentHeight2;
                }
                newSkyLine = appendString(newSkyLine, currentX, maxHeight);
                skyLineNowPoint1 += 2;
                skyLineCount1--;
            }
            else{
                currentX = skyLine2[skyLineNowPoint2];
                currentHeight2 = skyLine2[skyLineNowPoint2+1];
                maxHeight = currentHeight1;
                if(currentHeight2>maxHeight){
                    maxHeight = currentHeight2;
                }
                newSkyLine = appendString(newSkyLine, currentX, maxHeight);
                skyLineNowPoint2 += 2;
                skyLineCount2--;
            }
        }
        while(skyLineCount1>0){
            newSkyLine = appendString(newSkyLine,skyLine1[skyLineNowPoint1],skyLine1[skyLineNowPoint1+1]);
            skyLineNowPoint1 += 2;
            skyLineCount1--;
        }
        while(skyLineCount2>0){
            newSkyLine = appendString(newSkyLine,skyLine2[skyLineNowPoint2],skyLine2[skyLineNowPoint2+1]);
            skyLineNowPoint2 += 2;
            skyLineCount2--;
        }
        newSkyLine = removeOverlap(newSkyLine);
        return newSkyLine;
    }

    private int[] removeOverlap(int[] before){
        int[] after = new int[before.length];
        int sizeOfAfter = 3;
        System.arraycopy(before,0,after,0,3);
        for(int i = 4; i<before.length; i+=2){
            if(before[i] != before[i-2]){
                after[sizeOfAfter++] = before[i-1];
                after[sizeOfAfter++] = before[i];
            }
        }
        int[] result = new int[sizeOfAfter];
        System.arraycopy(after,0,result,0,sizeOfAfter);
        return result;
    }

    private int[] appendString(int[] before, int x, int y){
        int[] after = new int[before.length+2];
        System.arraycopy(before,0,after,0,before.length);
        after[before.length] = x;
        after[before.length+1] = y;
        return after;
    }

    class Building {
        int left;
        int right;
        int height;
    }

    public static void main(String[] args){
        SkyLine s = new SkyLine();
    }
}
