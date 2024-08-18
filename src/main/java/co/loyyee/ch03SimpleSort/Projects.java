package co.loyyee.ch03SimpleSort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Projects {

  private static final int MAX = 10000;
  private long[] a;
  private int n;

  public Projects(int max) {
    a = new long[max];
    n = 0;
  }

  public static void main(String[] args) {
    long[] rands =
        IntStream.range(0, MAX)
            .mapToLong((ignore) -> ThreadLocalRandom.current().nextInt(0, MAX))
            .toArray();
    var s = new Projects(MAX);
    s.batchInsert(rands);
    //    s.time(() -> s.bidirectionalBubble());
    //    s.time(() -> s.oddEvent());
    //    s.time(() -> s.bubble());
    //    s.time(() -> s.insertion());
    s.time(() -> s.insertionNoDups());
  }

  public void time(Runnable func) {
    long start = System.nanoTime();
    func.run();
    long end = System.nanoTime() - start;

    System.out.println("Time: " + (double) end / 1_000_000 + " ms");
  }

  /**
   * Cocktail shaker sort <hr> 3.1 In the bubbleSort.java program (Listing 3.1) and the BubbleSort
   * Workshop applet, the in index always goes from left to right, finding the largest item and
   * carrying it toward out on the right.
   *
   * <p>Modify the bubbleSort() method so that it’s bidirectional. This means the <em>in</em> index
   * will first carry the largest item from left to right as before, but when it reaches
   * <em>out</em>, it will reverse and carry the smallest item from right to left. You’ll need two
   * outer indexes, one on the right (the old out)and another on the left
   */
  public void bidirectionalBubble() {
    System.out.println("======Bidirectional Bubble Sort======");
    int rOut = n - 1;
    int lOut = 0;
    int in = 0;
    boolean swapped = true;

    while (swapped == true) {
      for (in = lOut; in < rOut; in++) { // left to right
        if (a[in] > a[in + 1]) {
          swap(in, in + 1);
          swapped = true;
        }
      }
      if (swapped == false) break;
      swapped = false;

      for (in = rOut - 1; in > lOut; in--) { // right to left
        if (a[in] < a[in - 1]) {
          swap(in, in - 1);
          swapped = true;
        }
      }
      rOut--;
      lOut++;
    }
  }

  /**
   * 3.2 Add a method called median() to the ArrayIns class in the insertSort.java program (Listing
   * 3.3). This method should return the median value in the array. (Recall that in a group of
   * numbers half are larger than the median and half are smaller.) Do it the easy way
   */
  public long median() {
    insertion();
    if (this.a.length % 2 == 0) {
      return a[n / 2 - 1];
    } else {
      return a[n / 2];
    }
  }

  /**
   * 3.3 To the insertSort.java program (Listing 3.3), add a method called noDups() that removes
   * duplicates from a previously sorted array without disrupting the order. (You can use the
   * insertionSort() method to sort the data, or you can simply use main() to insert the data in
   * sorted order.)
   *
   * <p>One can imagine schemes in which all the items from the place where a duplicate was
   * discovered to the end of the array would be shifted down one space every time a duplicate was
   * discovered, but this would lead to slow O(N2 ) time, at least when there were a lot of
   * duplicates.
   *
   * <p>In your algorithm, make sure no item is moved more than once, no matter how many duplicates
   * there are. This will give you an algorithm with O(N) time.
   */
  public long[] noDups() {
    insertion();
    long[] dedups = new long[a.length];
    int dupCount = 0;
    for (int i = 0, j = 1; j < n; j++) {
      if (a[i] == a[j] && dedups[i] != a[i]) {
        dedups[i] = a[i];
        dupCount++;
      } else if (a[i] != a[j] && dedups[i] != a[j]) {
        ++i;
        dedups[i] = a[j];
      }
    }
    n -= dupCount;
    return dedups;
  }

  /**
   * Brick Sort <hr> 3.4 Another simple sort is the odd-even sort. The idea is to repeatedly make
   * two passes through the array. On the first pass you look at all the pairs of items, a[j] and
   * a[j+1], where j is odd (j = 1, 3, 5, …). If their key values are out of order, you swap them.
   * On the second pass you do the same for all the even values (j = 2, 4, 6, …). You do these two
   * passes repeatedly until the array is sorted. Replace the bubbleSort() method in bubbleSort.java
   * (Listing 3.1) with an oddEvenSort() method. Make sure it works for varying amounts of data.
   * You’ll need to figure out how many times to do the two passes.
   *
   * <p>The odd-even sort is actually useful in a multiprocessing environment, where a separate
   * processor can operate on each odd pair simultaneously and then on each even pair. Because the
   * odd pairs are independent of each other, each pair can be checked—and swapped, if necessary—by
   * a different processor. This makes for a very fast sort.
   */
  public void oddEvent() {
    System.out.println("======(Brick) Odd Event Bubble Sort======");
    int in;
    boolean isSwap = true;
    while (isSwap) {
      isSwap = false;

      for (in = 1; in < n - 1; in += 2) {
        if (a[in] > a[in + 1]) {
          swap(in, in + 1);
          isSwap = true;
        }
      }

      for (in = 0; in < n - 1; in += 2) {
        if (a[in] > a[in + 1]) {
          swap(in, in + 1);
          isSwap = true;
        }
      }
    }
  }

  /**
   * 3.6 <br>
   * insertion and deduplication <hr> Here’s an interesting way to remove duplicates from an array.
   * The insertion sort uses a loop-within-a-loop algorithm that compares every item in the array
   * with every other item. If you want to remove duplicates, this is one way to start. (See also
   * Exercise 2.6 in Chapter 2.)
   *
   * <p>Modify the insertionSort() method in the insertSort.java program so that it removes
   * duplicates as it sorts. Here’s one approach: When a duplicate is found, write over one of the
   * duplicated items with a key value less than any normally used (such as –1, if all the normal
   * keys are positive).
   *
   * <p>Then the normal insertion sort algorithm, treating this new key like any other item, will
   * put it at index 0. From now on the algorithm can ignore this item. The next duplicate will go
   * at index 1, and so on. When the sort is finished, all the removed dups (now represented by –1
   * values) will be found at the beginning of the array. The array can then be resized and shifted
   * down so it starts at 0.
   */
  public void insertionNoDups() {
    System.out.println("======No Duplicates Insertion Sort======");
    int in, out;
    int dupCounts = 0;
    for (out = 1; out < n; out++) { // out is diving line
      long temp = a[out]; // remove marked item
//      System.out.println("Temp: " + temp);
      in = out; // start shifts at out
      while (in > 0 && a[in - 1] >= temp) { // until one is smaller
        if (a[in -1] == temp) { // duplicate found
          temp = -1; // mark as -1;
          dupCounts++;
        }
        a[in] = a[in - 1]; // shift item right
        --in; // go left one position
      }
      a[in] = temp;
    }
    System.out.println(dupCounts);
    if (dupCounts != 0) {
      for (int i = dupCounts; i < n; i++) {
        a[i - dupCounts] = a[i];
      }
    }
    n -= dupCounts;
        display();
  }

  public void bubble() {
    System.out.println("======Bubble Sort======");
    int out, in;
    for (out = n - 1; out > 1; out--) { // right to left
      for (in = 0; in < out; in++) { // left to right
        if (a[in] > a[in + 1]) {
          swap(in, in + 1);
        }
      }
    }
  }

  /**
   * Selection Sort <br>
   * Big O: O(N) (swap), O(N^2) (comparison)
   */
  public void selection() {
    System.out.println("======Selection Sort======");
    int out, in; // inner and outer	pointer
    int min; // idx of smallest

    int steps = 0;
    // n - 1  because the inner pointer will reach to the end
    for (out = 0; out < n - 1; out++) {
      min = out;
      for (in = out + 1; in < n; in++) {
        // swap as needed, min will continuous to change when the out pointer moves towards the
        // right
        if (a[in] < a[min]) {
          min = in;
        }
        steps++;
        // if the rhs is not smaller, the min will be the same as out, basically no swap
        swap(out, min);
        //        display();
      }
    }
    System.out.println("Steps count: " + steps);
  }

  /**
   * Insertion Sort <br>
   * Find marker, make space, move partially sorted group <br>
   * Fastest in all simple sort Big O: O(N^2)
   */
  public void insertion() {
    System.out.println("======Insertion Sort======");
    int out, in; // outer and inner pointer
    int steps = 0;

    // 3.5
    int copiedCount = 0;
    int comparisonCount = 0;

    for (out = 1; out < n; out++) { // out is diving line
      long temp = a[out]; // remove marked item
      copiedCount++;
      //      System.out.println("Temp: "+ temp);
      in = out; // start shifts at out
      while (in > 0 && a[in - 1] >= temp) { // until one is smaller
        a[in] = a[in - 1]; // shift item right
        --in; // go left one position
        comparisonCount++;
        copiedCount++;
      }
      a[in] = temp; // insert marked item
      steps++;
      copiedCount++;
    }
    // 3.5
    System.out.printf(
        "Steps count: %d\nCopied count: %d\nComparison count: %d\n",
        steps, copiedCount, comparisonCount);
  }

  public void insert(long value) {
    a[n] = value;
    n++;
  }

  /** only use in the beginning when the array is 0s */
  public void batchInsert(long[] values) {
    var empty = Arrays.stream(a).allMatch(el -> el == 0);

    if (!empty) return;
    if (values.length != a.length) return;
    a = values;
    n = values.length;
  }

  public void display() {
    Arrays.stream(a).forEach(el -> System.out.print(el + ", "));
    System.out.println("");
  }

  /** reset with a max capacity */
  public void reset(int max) {
    a = new long[max];
  }

  private void swap(int lhs, int rhs) {

    long temp = a[lhs];
    a[lhs] = a[rhs];
    a[rhs] = temp;
  }
}
