import java.util.Scanner;

public class ManhattanTourists {

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

        System.out.println(tourism());

    }

    public int tourism() {

    }


    public static void main(String[] args) {
        new ChocolateAgency().run();
    }
}
