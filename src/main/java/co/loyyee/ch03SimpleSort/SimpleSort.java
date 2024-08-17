package co.loyyee.ch03SimpleSort;

import java.util.Arrays;

/**
 * Algorithm from the book.
 * <br>
 * To see the steps, uncomment the sout and display() in each sorting method.
 * */
public class SimpleSort {
  private long[] a;
  private int n;

  public SimpleSort(int max) {
    a = new long[max];
    n = 0;
  }

  public static void main(String[] args) {
    var sort = new SimpleSort(5);
    sort.insert(10);
    sort.insert(5);
    sort.insert(1);
    sort.insert(6);
    sort.insert(9);
//    sort.bubble();

    sort.reset(5);
    sort.batchInsert(new long[] {10, 9, 1, 7, 2});
//    sort.selection();

    sort.reset(5);
    sort.batchInsert(new long[] {10, 9, 1, 7, 2});
    sort.insertion();
  }

  /**
   * Bubble Sort - Slow <br>
   * Go through 0 -> N-1 ... N-N <hr> Big O: O(N^2/M) therefore O(N^2)
   */
  public void bubble() {
    System.out.println("======Bubble Sort======");
    int out, in; // inner and outer	pointer
    int steps = 0;

    // outer loop from backward last to first
    for (out = n - 1; out > 1; out--) {
      // inner loop from first to current max (out)
      for (in = 0; in < out; in++) {
//        display();
        steps++;
        if (a[in] > a[in + 1]) {
          swap(in, in + 1);
        }
      }
    }
    System.out.println("Steps count: " + steps);
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

    for (out = 1; out < n; out++) { // out is diving line
      long temp = a[out]; // remove marked item
//      System.out.println("Temp: "+ temp);
      in = out; // start shifts at out
//      display();
//      System.out.println("Before in: " + in);
//      System.out.println("Before out: " + out);
      while (in > 0 && a[in - 1] >= temp) { // until one is smaller
        a[in] = a[in - 1]; // shift item right
        --in; // go left one position
//        display();
//        System.out.println("Swapping in: " + in);
//        System.out.println("Swapping out: " + out);
      }
//      System.out.println("After in: " + in);
//      System.out.println("After out: " + out);
      a[in] = temp; // insert marked item
      steps++;
//      display();
//      System.out.println("--------");
    }
    System.out.println("Steps count: " + steps);
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
