/**
 * Created by jegalsumin on 2016. 10. 13..
 */
import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci {

    BigInteger input;
    int userWantMethod;
    long startTime, endTime;
    BigInteger result;

    public Fibonacci() {
        Scanner scan = new Scanner(System.in);
        System.out.print("What do you want to put input value? ");
        input = scan.nextBigInteger();
        System.out.print("What method do you want? (1:recursion, 2:array, 3:recursive squaring) ");
        userWantMethod = scan.nextInt();
        switch (userWantMethod){
            case 1:
                for(int i = 0; input.compareTo(BigInteger.valueOf(i)) >= 0; i++){
                    startTime = System.nanoTime();
                    result = fibonacci_recursion(BigInteger.valueOf(i));
                    endTime = System.nanoTime();
                    if((i%10)==0){
                        System.out.println("----------------------------------------------------");
                    }
                    System.out.printf("<%3d> = %20d %10d nano sec\n",i,result,(endTime-startTime));
                }
                break;
            case 2:
                for(int i = 0; input.compareTo(BigInteger.valueOf(i)) >= 0; i++){
                    startTime = System.nanoTime();
                    result = fibonacci_array(BigInteger.valueOf(i));
                    endTime = System.nanoTime();
                    if((i%10)==0){
                        System.out.println("----------------------------------------------------");
                    }
                    System.out.printf("<%3d> = %20d %10d nano sec\n",i,result,(endTime-startTime));
                }
                break;
            case 3:
                for(int i = 0; input.compareTo(BigInteger.valueOf(i)) >= 0; i++){
                    startTime = System.nanoTime();
                    result = fibonacci_recursiveSquaring(BigInteger.valueOf(i));
                    endTime = System.nanoTime();
                    if((i%10)==0){
                        System.out.println("----------------------------------------------------");
                    }
                    System.out.printf("<%3d> = %20d %10d nano sec\n",i,result,(endTime-startTime));
                }
                break;
            default:
                System.out.println("Wrong method");
                break;
        }
    }

    public BigInteger fibonacci_recursion(BigInteger input){
        if(input.compareTo(BigInteger.valueOf(2)) < 0)
            return input;
        return fibonacci_recursion(input.subtract(BigInteger.valueOf(1))).add(fibonacci_recursion(input.subtract(BigInteger.valueOf(2))));
    }

    public BigInteger fibonacci_array(BigInteger input){
        if(input.compareTo(BigInteger.valueOf(2)) < 0){
            return input;
        }
        BigInteger result = BigInteger.ZERO;
        BigInteger before1 = BigInteger.ZERO;
        BigInteger before2 = BigInteger.ONE;
        for(int i = 1; i < input.longValue(); i++){
            result = before1.add(before2);
            before1 = before2;
            before2 = result;
        }
        return result;
    }

    public BigInteger fibonacci_recursiveSquaring(BigInteger input){
        BigInteger[][] basic = new BigInteger[][]{{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
        if(input.compareTo(BigInteger.valueOf(2)) < 0){
            return input;
        }
        return pow(basic,input)[0][1];
    }

    private BigInteger[][] pow(BigInteger[][] matrix, BigInteger input){
        if(input.compareTo(BigInteger.valueOf(1)) == 0){
            return matrix;
        }
        if((input.remainder(BigInteger.valueOf(2))).compareTo(BigInteger.valueOf(0)) == 0){
            return multiply(pow(matrix,input.divide(BigInteger.valueOf(2))),
                    pow(matrix,input.divide(BigInteger.valueOf(2))));
        }
        else{
            BigInteger[][] result =
                    multiply(pow(matrix,(input.subtract(BigInteger.valueOf(1))).divide(BigInteger.valueOf(2))),
                    pow(matrix,(input.subtract(BigInteger.valueOf(1))).divide(BigInteger.valueOf(2))));
            return multiply(result, matrix);
        }
    }

    private BigInteger[][] multiply(BigInteger[][] inputMatrix1, BigInteger[][] inputMatrix2){
        BigInteger[][] result = new BigInteger[][]{{BigInteger.ZERO, BigInteger.ZERO}, {BigInteger.ZERO, BigInteger.ZERO}};
        result[0][0] = (inputMatrix1[0][0].multiply(inputMatrix2[0][0])).add(inputMatrix1[0][1].multiply(inputMatrix2[1][0]));
        result[0][1] = (inputMatrix1[0][0].multiply(inputMatrix2[0][1])).add(inputMatrix1[0][1].multiply(inputMatrix2[1][1]));
        result[1][0] = (inputMatrix1[1][0].multiply(inputMatrix2[0][0])).add(inputMatrix1[1][1].multiply(inputMatrix2[1][0]));
        result[1][1] = (inputMatrix1[1][0].multiply(inputMatrix2[0][1])).add(inputMatrix1[1][1].multiply(inputMatrix2[1][1]));
        return result;
    }

    public static void main(String[] args){
        Fibonacci f = new Fibonacci();
    }

}
