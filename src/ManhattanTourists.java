import java.util.Arrays;
import java.util.Scanner;

public class ManhattanTourists {



    int size;
    public int[][] r;
    public int[][] d;
    public int[][] m;

    public void run() {

        Scanner sc = new Scanner(System.in);
        size = sc.nextInt(); // Size = 1 - 1000
        r = new int[size][size-1];
        d = new int[size-1][size];
        m = new int[size][size];
        for (int[] row : m) //Initialize entire multidimensional array to -1
            Arrays.fill(row,-1);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size-1; j++) {
                r[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < size-1; i++) {
            for (int j = 0; j < size; j++) {
                d[i][j] = sc.nextInt();
            }
        }

        //System.out.println(Arrays.deepToString(r));
        //System.out.println(Arrays.deepToString(d));
        System.out.println(tourism(size-1,size-1));
        //System.out.println(Arrays.deepToString(m));

    }
    /**
     * Recurrence: opt (i,j) =  { 0              -> if i = 0 and j = 0
     *                          { vi + opt(i-1,0)-> if i > 0 and j = 0
     *                          { vj + opt(0,j-1)-> if j > 0 and i = 0
     *                          { max of { vi + opt(i-1,j)
     *                                   { vj + opt(i,j-1)
     */
    public int tourism(int i, int j) {
        if (m[i][j] != -1) {
            return m[i][j]; //Utilize memoization.
        }

        if (i == 0 && j == 0) {
            m[i][j] = 0;
        }
        else if (i > 0 && j == 0) {
            m[i][j] = tourism(i-1,j)+d[i-1][j];
        }
        else if (i == 0 && j > 0) {
            m[i][j] = tourism(i,j-1)+r[i][j-1];
        }
        else {
            m[i][j] = (Math.max(tourism(i-1,j)+d[i-1][j],tourism(i,j-1)+r[i][j-1]));
        }
        return m[i][j];
    }



    public static void main(String[] args) {
        new ManhattanTourists().run();
    }
}
