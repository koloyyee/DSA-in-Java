package co.loyyee.ch03SimpleSort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Experiments {
  private long[] a;
  private int n;
  private static final int MAX = 10_000;

  public static void main(String[] args) {
    exp1();
    exp2();
    exp3();
  }
  
  public static void exp3() {
    System.out.println("Experiment 3: Sorted 10K (0, 1, 2 ,3 .... 9999) ");
    long[] sorted = IntStream.range(0, MAX).mapToLong((n) -> n).toArray();
    var  e = new SimpleSort(MAX);
    
    e.batchInsert(sorted);
    long start = System.nanoTime();
    e.bubble();
    long end = System.nanoTime() - start;
    System.out.println("Bubble Sort: " + (double) end / 1_000_000 + " ms");
    
    e.reset(MAX);
    e.batchInsert(sorted);
    start = System.nanoTime();
    e.selection();
    end = System.nanoTime() - start;
    System.out.println("Selection Sort: " + (double) end / 1_000_000 + " ms");
    
    e.reset(MAX);
    e.batchInsert(sorted);
    start = System.nanoTime();
    e.insertion();
    end = System.nanoTime() - start;
    System.out.println("Insertion Sort: " + (double) end / 1_000_000 + " ms");
    System.out.println();
  }
  
 public static void exp2() {
   System.out.println("Experiment 2: Sort 1K row with (99, 999, 99, 998...., 99,0)");
    long[] kala= IntStream.range(0,MAX).mapToLong( (i) -> {
      if( i % 2 == 0 )  {
        return 99;
      }
      return MAX - i;
    }).toArray();
    
    var e = new SimpleSort(MAX);
    e.batchInsert(kala);
   
   long start = System.nanoTime();
    e.bubble();
   long end = System.nanoTime() - start;
   System.out.println("Bubble Sort: " + (double) end / 1_000_000 + " ms");
   
   e.reset(MAX);
   e.batchInsert(kala);
   start = System.nanoTime();
   e.selection();
   end = System.nanoTime() - start;
   System.out.println("Selection Sort: " + (double) end / 1_000_000 + " ms");
   
   e.reset(MAX);
   e.batchInsert(kala);
   start = System.nanoTime();
   e.insertion();
   end = System.nanoTime() - start;
   System.out.println("Insertion Sort: " + (double) end / 1_000_000 + " ms");
    System.out.println();
 }
  public static void exp1() {
    System.out.println("Experiment 1: Sort 10K.");
    // Experiment 1
    var e = new SimpleSort(MAX);
    
    long[] tenK = IntStream.range(0, MAX).mapToLong((ignore) -> ThreadLocalRandom.current().nextInt(MAX)).toArray();
    
    e.batchInsert(tenK);
    long start = System.nanoTime();
    e.bubble();
    long end = System.nanoTime() - start;
    System.out.println("Bubble Sort: " + (double) end / 1_000_000 + " ms");
    
    e.reset(MAX);
    e.batchInsert(tenK);
    start = System.nanoTime();
    e.selection();
    end = System.nanoTime() - start;
    System.out.println("Selection Sort: " + (double) end / 1_000_000 + " ms");
    
    e.reset(MAX);
    e.batchInsert(tenK);
    start = System.nanoTime();
    e.insertion();
    end = System.nanoTime() - start;
    System.out.println("Insertion sort: " + (double) end / 1_000_000 + " ms");
    System.out.println();
    
  }
}
