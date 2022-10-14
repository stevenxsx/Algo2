public class DynamicProgramming {

    public static void main(String[] args) { new DynamicProgramming().run(); }

    public void run() {
        int n = 25;
        int[] memoize = new int[n+1];

        long optimizedStart = System.nanoTime();
        int optimized = fibonacciOptimized(n, memoize);
        long optimizedStop = System.nanoTime();
        long exponentialStart;
        long exponentialStop;
        exponentialStart= System.nanoTime();
        int exponential = fibonacciExponential(n);
        exponentialStop = System.nanoTime();
        System.out.println("Fibonacci Optimized result -> " + optimized);
        System.out.println("Optimized solution took " + (optimizedStop-optimizedStart) + " nanoseconds.");
        System.out.println("Fibonacci Exponential result -> " + exponential);
        System.out.println("Exponential solution took " + (exponentialStop-exponentialStart) + " nanoseconds.");
        System.out.println("Exponential solution took " + ((exponentialStop-exponentialStart) / (optimizedStop-optimizedStart)) + " times the optimized runtime.");

    }

    public int fibonacciOptimized(int n, int[] memoize) {
        if (memoize[n] != 0) { //If the value has already been calculated before.
            return memoize[n];
        }
        if (n == 1 || n == 2) { //Fibonacci sequence starts with 1, 1
            return 1;
        }
        else {
            int result = fibonacciOptimized(n-1,memoize) + fibonacciOptimized(n-2,memoize); //Recursion using memoize
            memoize[n] = result;
            return result;
        }
    }

    public int fibonacciExponential(int n) {
        if (n == 1 || n == 2) { //Fibonacci sequence starts with 1, 1
            return 1;
        }
        else {
            return fibonacciExponential(n-1) + fibonacciExponential(n-2);
        }
    }
}
