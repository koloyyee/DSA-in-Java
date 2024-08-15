package co.loyyee.ch02Arrays;

import java.util.Arrays;
import java.util.Scanner;

///usr/bin/env jbang "$0" "$@" ; exit $?

/**
 * Basic Array in Java
 * 
 * @author David Ko
 */
public class BasicArray {

  int[] nums = null;

  public BasicArray(int[] nums) {
    this.nums = nums;
  }

  public static void main(String[] args) {

    // int[] input = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
    try (Scanner sc = new Scanner(System.in)) {
      String input = sc.nextLine();
      input = !input.isBlank() || !input.isEmpty() ? input : """
          1, 3, 7, 10, 12, 15, 18, 21, 23, 26, 29, 31, 34, 37, 39, 42, 45, 47, 50,
          53, 55, 58, 61, 63, 66, 69, 71, 74, 77, 79, 82, 85, 87, 90, 93, 95,
          98, 101, 103, 106, 109, 111, 114, 117, 119, 122, 125, 127, 130, 133
               """;
      int[] nums = Arrays.stream(input.split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
      BasicArray arr = new BasicArray(nums);
      // var found = arr.findLin(3);
      // System.out.println(found);
      // var binf = arr.findBin(3);
      // System.out.println(binf);
      arr.addFront(0);
      arr.addFront(101);

    }
  }

  void insert(int newVal) {

  }

  void addFront(int val) {
    int[] newNums = new int[this.nums.length + 1];
    newNums[0] = val;
    for (int i = 0; i < this.nums.length; i++) {
      newNums[i + 1] = this.nums[i];
    }
    nums = newNums;
    System.out.println(nums[0]);
  }

  void addLast(int val) {
    int[] newNums = new int[this.nums.length + 1];
    for (int i = 0; i < this.nums.length; i++) {
      newNums[i + 1] = this.nums[i];
    }
    newNums[newNums.length] = val;
    nums = newNums;
    System.out.println(nums[nums.length]);
  }

  int findLin(int val) {
    for (int n : nums) {
      if (n == val) {
        return n;
      }
    }
    return nums.length;
  }

  /** Linear search */
  int findBin(int val) {
    return findBin(0, nums.length, val);
  }

  /** Binary Search - my own implementation */
  int findBin(int lo, int hi, int val) {
    int mid = lo + (hi - lo) / 2;
    System.out.println("lo: " + lo + " hi: " + hi + " val: " + val);
    if (nums[mid] == val) {
      return nums[mid];
    }
    if (nums[mid] < val) {
      return findBin(mid + 1, hi, val);
    }
    if (nums[mid] > val) {
      return findBin(lo, mid - 1, val);
    }
    return nums.length;
  }

  /** implementation from the book */
  public int find(int searchKey) {
    int lo = 0;
    int hi = nums.length - 1;
    int curIn;

    while (true) {
      curIn = (lo + hi) / 2;

      if (nums[curIn] == searchKey) {
        return curIn;

      } else if (lo > hi) {
        return nums.length;

      } else {
        if (nums[curIn] < searchKey) {
          lo = curIn + 1;
        } else {
          hi = curIn - 1;
        }
      }
    }
  }
}
