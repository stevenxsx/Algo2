import java.util.*;

public class Introduction {
    public static void main(String[] args) {
        new Introduction().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            numbers.add(sc.nextInt());
        }
        Collections.reverse(numbers);
        for (int q:numbers
             ) {
            System.out.print(q + " ");
        }
    }

    public void runFaster() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = sc.nextInt();
        }
        for (int i = size; i > 0; i--) {
            System.out.print(numbers[i-1] + " ");
        }
    }
}
