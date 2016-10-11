/**
 * Created by jegalsumin on 2016. 10. 7..
 */
import java.util.Scanner;

public class ClockAngle {

    int hour;
    int minute;
    double hourAngle;
    double minuteAngle;

    public ClockAngle(){
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.print("(if you want to finish, input higher than 25) hour : ");
            hour = scan.nextInt();
            if(hour >= 25){
                return;
            }
            else if(hour>12){
                hour = hour-12;
            }
            System.out.print("minute: ");
            minute = scan.nextInt();
            angleIs();
            besidesAngle();
        }
    }

    private void angleIs(){
        hourAngle = ((hour*30)+(minute*0.5));
        minuteAngle = (minute*6);
    }

    public void besidesAngle(){
        double angle;
        angle = hourAngle - minuteAngle;
        if(angle<0){
            angle = minuteAngle - hourAngle;
        }
        if(angle>180){
            double temp = angle - 180;
            angle = 180 - temp;
        }
        System.out.println("Angle is " + angle + "∘");
    }

    public static void main(String[] args){
        ClockAngle c = new ClockAngle();
    }
}
