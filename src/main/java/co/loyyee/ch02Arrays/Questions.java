///usr/bin/env jbang "$0" "$@" ; exit $?

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 1. Inserting an item into an unordered array
 * a. takes time proportional to the size of the array.
 * b. requires multiple comparisons.
 * c. requires shifting other items to make room.
 * d. takes the same time no matter how many items there are
 */
public class Questions {

    public static void main(String... args) {
        int[] unordered = getUA();
        Arrays.stream(unordered).forEach(System.out::println);
        System.out.println("1a: O(N)");
    }

    /** Creating an unordered array of 10 */
    static int[] getUA() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int[] arr = new int[100];
        for (int i = 0; i < 10; i++) {
            int randVal = rand.nextInt(0, arr.length + 1);
            arr[i] = randVal;
        }
        return arr;
    }

    private static long start() {
        long now = System.nanoTime();
        System.out.println(now);
        return now;
    }
    private static long end(long start) {
        long now = System.nanoTime();
        System.out.println(now);
        return now - start;
    }
}

