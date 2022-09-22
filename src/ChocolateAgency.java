import java.util.Arrays;
import java.util.Scanner;

public class ChocolateAgency {

    int size;
    public int[] s;
    public int[] b;

    public void run() {
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();
        s = new int[size];
        b = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = sc.nextInt();
        }
        for (int i = 0; i < size; i++) {
            b[i] = sc.nextInt();
        }

        System.out.println(choccy(s, b, 0,size));

    }

    public int choccy(int[] a, int[] b, int i, int j) {
        int half = (i+j)/2;
        if (j-i == 1) {
            //System.out.println("SingleDayProfit("+i+","+j+") =  = " + (b[i]-a[i]));
            return (Math.max(b[i] - a[i], 0)); //returns profit for a single day
        }
        else {
            int left = choccy(a,b,i,half);
            int right = choccy(a,b,half,j);

            int leftMin = Integer.MAX_VALUE;
            for (int k = i; k < half; k++) {
                if (a[k] + (half-k) * 100 < leftMin) { //calculate lowest buy for each day if selling after a specific day.
                    leftMin = a[k] + (half-k) * 100;
                }
            }
            //System.out.println("LeftMin("+i+","+j+") = " + leftMin);
            int rightMax = 0;
            for (int k = half; k < j; k++) {
                if (b[k] - (k-half) * 100 > rightMax) { //calculate highest sell for selling after a specific day.
                    rightMax = b[k] - (k-half) * 100;
                }
            }
            //System.out.println("RightMax("+i+","+j+") = " + rightMax);
            return Math.max(Math.max(left,right),rightMax-leftMin);

        }

    }



    public static void main(String[] args) {
        new ChocolateAgency().run();
    }


}

