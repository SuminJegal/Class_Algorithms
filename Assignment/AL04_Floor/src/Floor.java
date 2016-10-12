/**
 * Created by jegalsumin on 2016. 10. 11..
 */
import java.util.Scanner;
import java.math.BigInteger;

public class Floor {

    long input = 1;

    public Floor(){
        Scanner scan = new Scanner(System.in);
        while(input>0){
            System.out.print("input data : ");
            input = scan.nextLong();
            notBinary();
            binary();
        }

    }

    public void notBinary(){
        int e = -1;
        long k = 1;
        do{
            e = e + 1;
            k = k * 2;
        }while(k <= input);
        System.out.println("(not binary)Floor = " + e);
    }

    public void binary(){
        int beforeE;
        int e = 1;
        BigInteger k = BigInteger.valueOf(2);
        BigInteger[] checkValue = new BigInteger[65];
        checkValue[e] = k;
        do{
            beforeE = e;
            e = e * 2;
            k = k.multiply(k);
            checkValue[e] = k;
        }while(k.compareTo(BigInteger.valueOf(input)) <= 0);
        while((e-beforeE)>1){
        int gap = e - beforeE;
            gap = gap/2;
            checkValue[beforeE+gap] = checkValue[e].divide(checkValue[gap]);
            if(checkValue[beforeE+gap].compareTo(BigInteger.valueOf(input))==0){
                break;
            }
            else if(checkValue[beforeE+gap].compareTo(BigInteger.valueOf(input))>0){
                e = beforeE + gap;
            }
            else{
                beforeE = beforeE + gap;
            }
        }
        System.out.println("(binary)Floor = " + beforeE);
    }

    public static void main(String[] args){
        Floor f = new Floor();
    }
}
