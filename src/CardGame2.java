import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class CardGame2 {

    public static void main(String[] args) {
        long optimizedStart;
        long optimizedStop;

        Hashtable<List<Integer> , Integer> states=new Hashtable<>();
        ArrayList<Integer> cards = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        for (int i = 0; i < size; i++) {
            cards.add(sc.nextInt());
        }

        //Measuring runtime of functions.
        optimizedStart = System.nanoTime();
        int maxValue = solve(states, cards, true);
        System.out.println(maxValue);
        optimizedStop = System.nanoTime();
        System.out.println(optimizedStop-optimizedStart + " nanoseconds (1E9)");

    }
    public static int solve(Hashtable<List<Integer> , Integer> states, List<Integer> cards, boolean me) {
        int value = 0;
        if (cards.size() == 0) {
            return 0;
        }
        if (states.containsKey(cards)) {
            return states.get(cards);

        }
        else if (!me) {
            if (cards.get(0) > cards.get(cards.size() - 1)) {
                value = solve(states, cards.subList(1, cards.size()), true);
            }
            else {
                value = solve(states, cards.subList(0, cards.size()-1), true);
            }
        }
        else {
            value = Math.max(cards.get(0) + solve(states,
                            cards.subList(1, cards.size()), false),
                    cards.get(cards.size()-1) + solve(states,
                            cards.subList(0, cards.size() - 1), false));
        }
        states.put(cards, value);
        return value;
    }
}