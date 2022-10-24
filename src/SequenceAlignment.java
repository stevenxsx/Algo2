import java.util.Arrays;
import java.util.Scanner;

public class SequenceAlignment {
    public static void main(String[] args) {
        new SequenceAlignment().run();
    }

    final int d = 1; //Delta (gap penalty)
    int m;
    int n;
    int[][] memo;
    direction[][] dir;
    String x;
    String y;
    String outputX;
    String outputY;

    public void run() {
        Scanner sc = new Scanner(System.in);
        x = sc.nextLine(); //String X
        y = sc.nextLine(); //String Y
        m = x.length(); //Length m of x
        n = y.length(); //Length n of y
        memo = new int[m + 1][n + 1];
        dir = new direction[m + 1][n + 1];
        for (int[] row : memo) { //Initialize entire multidimensional array to -1
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < m; i++) { //Initialize first row with the only possible values being skip costs.
            memo[i][0] = i * d;
            dir[i][0] = direction.l;
        }
        dir[m][n] = direction.d;
        for (int j = 0; j < n; j++) { //The same for the first column
            memo[0][j] = j * d;
            dir[0][j] = direction.u;
        }

        /*for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                opt(i,j);
            }
        }*/
        System.out.println(opt(m - 1, n - 1));
        //System.out.println(Arrays.deepToString(memo));
        calculateOutput();
        printDir();
        System.out.println(outputX);
        System.out.println(outputY);

    }

    public enum direction {l, u, d,o}

    public int opt(int i, int j) {
        /**
         * Recurrence:
         * (I)   (m,n) in M; OR
         * (II)  the m'th position of X is not matched; OR
         * (III) the n'th position of Y is not matched
         *
         * opt(I,J) = minimum cost of alignment between x1,x2,...xI and y1,y2,...yJ
         *
         * if (I) holds, we pay axMyN and then align substring x1,x2,...xM-1 as well as possible with y1,y2,...yN-1.
         * We get opt(m,n) = axMyN + opt(m-1,n-1).
         *
         * If (II) holds, we pay gap cost (δ) since then m'th position of X isnt matched, and then align x1,x2,...xM-1 as
         * well as possible with y1,y2,...yN
         * We get opt(m,n) = δ + opt(m-1,n)
         *
         * Similarly, with case (III), we get opt(m,n) = δ + opt(m,n-1)
         */

        if (memo[i][j] != -1) {
            return memo[i][j]; //Utilize memoization.
        }
        //return memo[i][j] = Math.min(Math.min(d + opt(i - 1, j),d + opt(i, j - 1)),matchCost(x.charAt(i), y.charAt(j)) + opt(i - 1, j - 1));

        int one = matchCost(x.charAt(i), y.charAt(j)) + opt(i - 1, j - 1);
        int two = d + opt(i - 1, j);
        int three = d + opt(i, j - 1);

        //Returns the smallest of the 3 possible cases, while also setting the value in the matrix, and direction in the other
        if (three < two && three < one) {
            //up
            dir[i][j] = direction.u;
            memo[i][j] = three;
            return three;
        } else if (two < one && two < three) {
            //left
            dir[i][j] = direction.l;
            memo[i][j] = two;
            return two;
        } else {
            //diagonal
            dir[i][j] = direction.d;
            memo[i][j] = one;
            return one;
        }

    }

    public void calculateOutput() {
        outputX = "";
        outputY = "";

        int i = m;
        int j = n;

        while (!(i == 0 && j == 0)) { //When we reach (0,0) it terminates.
            direction d = dir[i][j];
            dir[i][j] = direction.o;
            if (d == direction.d) {
                outputX = x.charAt(i - 1) + outputX;
                outputY = y.charAt(j - 1) + outputY;
                i--;
                j--;
            } else if (d == direction.u) {
                outputX = "-" + outputX;
                outputY = y.charAt(j - 1) + outputY;
                j--;
            } else if (d == direction.l) {
                outputX = x.charAt(i - 1) + outputX;
                outputY = "-" + outputY;
                i--;
            } else {
                System.out.println("error");
            }
            System.out.println(outputX);
            System.out.println(outputY);
        }

    }

    public void printDir() {
        for (int i = 0; i < dir[0].length; i++) {
            for (int j = 0; j < dir.length; j++) {
                int k = memo[j][i];
                if (k < 10) {
                    System.out.print("[0");
                }
                else { System.out.print("["); }
                System.out.print(memo[j][i] + "]");
            }
            System.out.println("");
        }
    }

    public int matchCost(char a, char b) {
        if (a == b) {
            return 0;
        }
        return 1; //All mismatch costs are 1 in this case. Normally, a matrix could represent the different mismatch costs.
    }
}
