import java.util.*;


public class CardGame {


    int size;
    int[] cards;
    boolean me;
    HashMap<String, Integer> table = new HashMap<>();

    int p1 = 0; //Index 1
    int p2 = 0; //Index 2

    public void run() {
        long optimizedStart;
        long optimizedStop;

        Scanner sc = new Scanner(System.in);
        size = sc.nextInt(); // Size = 1 - 2000
        cards = new int[size];
        p2 = size-1;
        for (int i = 0; i < size; i++) {
            cards[i] = sc.nextInt();
        }
        //Measuring runtime of functions.
        optimizedStart = System.nanoTime();
        System.out.println(easy(p1,p2, true));
        optimizedStop = System.nanoTime();
        System.out.println(optimizedStop-optimizedStart + " nanoseconds (1E9)");
    }

    public int easy(int p1, int p2, boolean me) { //2 indices and who's turn it is.
        int maxValue;

        if (p1 > p2) {
            return 0;
        }

        String key = p1 + " " + p2;
        if (table.containsKey(key)) { //Key doesn't already exist.
            return table.get(key);
        }
        else if (!me) { //His turn
            if (cards[p1] > cards[p2]) {
                maxValue = easy(p1+1,p2,true);
            }
            else {
                maxValue = easy(p1,p2-1,true);
            }
        }
        else { //My turn
            maxValue = Math.max(cards[p1] + easy(p1+1,p2,false), cards[p2] + easy(p1,p2-1,false));
            table.put(key,maxValue);

        }
        return maxValue;
    }


    public static void main(String[] args) {
        new CardGame().run();
    }
}
