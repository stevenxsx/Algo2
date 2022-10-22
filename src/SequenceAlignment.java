import java.util.Scanner;

public class SequenceAlignment {
    public static void main(String[] args) {
        new SequenceAlignment().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        final int d = 1; //Delta (gap penalty)
        String x = sc.nextLine(); //String X
        String y = sc.nextLine(); //String Y
        int m = x.length(); //Length m of x
        int n = y.length(); //Length n of y
        int[][] memo = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            memo[i][0] = i*d;
        }
        for (int j = 0; j < n; j++) {
            memo[0][j] = j*d;
        }

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                align(i,j);
            }
        }
        System.out.println(memo[m][n]);

    }

    public void align(int x, int y) {
        /**
         * Recurrence:
         * (I)   (m,n) in M; OR
         * (II)  the m'th position of X is not matched; OR
         * (III) the n'th position of Y is not matched
         *
         * opt(I,J) = minimum cost of alignment between x1,x2,...xI and y1,y2,...yJ
         * if (I) holds, we pay axMyN and then align substring x1,x2,...xM-1 as well as possible with y1,y2,...yN-1.
         * We get opt(m,n) = axMyN + opt(m-1,n-1).
         *
         * If (II) holds, we pay gap cost (δ) since then m'th position of X isnt matched, and then align x1,x2,...xM-1 as
         * well as possible with y1,y2,...yN
         * We get opt(m,n) = δ + opt(m-1,n)
         *
         * Similarly, with case (III), we get opt(m,n) = δ + opt(m,n-1)
         */




    }

    public int mismatchCost(char a, char b) {
        if (a == b) {
            return 0;
        }
        return 1; //All mismatch costs are 1 in this case. Normally, a matrix could represent the different mismatch costs.
    }
}
