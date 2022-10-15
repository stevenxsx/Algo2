public class DynamicProgramming {

    public static void main(String[] args) { new DynamicProgramming().run(); }

    /**
     * Dynamic Programming is mainly an optimization over plain recursion. Wherever we see a recursive solution that
     * has repeated calls for same inputs, we can optimize it using Dynamic Programming. The idea is to simply store
     * the results of subproblems, so that we do not have to re-compute them when needed later.
     */

    public void run() {
        int n = 45; //No more than 45!
        long[] memoize = new long[n+1];

        long optimizedStart;
        long optimizedStop;
        long exponentialStart;
        long exponentialStop;
        long bottomUpStart;
        long bottomUpStop;

        //Measuring runtime of functions.
        optimizedStart = System.nanoTime();
        long optimized = fibonacciOptimized(n, memoize);
        optimizedStop = System.nanoTime();

        exponentialStart = System.nanoTime();
        long exponential = fibonacciExponential(n);
        exponentialStop = System.nanoTime();

        bottomUpStart = System.nanoTime();
        long bottomUp = fibonacciBottomUp(n);
        bottomUpStop = System.nanoTime();

        System.out.println("Fibonacci Optimized result -> " + optimized);
        System.out.println("Optimized solution took " + (optimizedStop-optimizedStart) + " nanoseconds.");
        System.out.println("Fibonacci Exponential result -> " + exponential);
        System.out.println("Exponential solution took " + (exponentialStop-exponentialStart) + " nanoseconds.");
        System.out.println("Fibonacci BottomUp result -> " + bottomUp);
        System.out.println("BottomUp solution took " + (bottomUpStop-bottomUpStart) + " nanoseconds.");
        System.out.println("Exponential solution took " + ((exponentialStop-exponentialStart) / (optimizedStop-optimizedStart)) + " times the optimized runtime.");
        System.out.println("Exponential solution took " + ((exponentialStop-exponentialStart) / (bottomUpStop-bottomUpStart)) + " times the bottomUp runtime.");

    }

    public long fibonacciOptimized(int n, long[] memoize) {
        if (memoize[n] != 0) { //If the value has already been calculated before.
            return memoize[n];
        }
        if (n == 1 || n == 2) { //Fibonacci sequence starts with 1, 1
            return 1;
        }
        else {
            long result = fibonacciOptimized(n-1,memoize) + fibonacciOptimized(n-2,memoize); //Recursion using memoize
            memoize[n] = result;
            return result;
        }
    }

    public long fibonacciExponential(int n) {
        if (n == 1 || n == 2) { //Fibonacci sequence starts with 1, 1
            return 1;
        }
        else {
            return fibonacciExponential(n-1) + fibonacciExponential(n-2);
        }
    }

    public long fibonacciBottomUp(int n) {
        long one = 1;
        long two = 1;
        for (int i = 3; i < n; i++) {
            if (i%2 == 1) {
                two = one+two;
            }
            else { one = one+two; }
        }
        return one+two;
    }
}
