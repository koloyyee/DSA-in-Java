package co.loyyee.ch02Arrays;

public class HighArray {
  private long[] a;
  private int nElems;

  public HighArray(int max) {
    a = new long[max];
    nElems = 0;
  }

  public boolean find(long searchKey) {
    int j;
    for (j = 0; j < nElems; j++) {
      if (a[j] == searchKey) {
        break;
      }
    }
    if (j == nElems) {
      return false;
    } else {
      return true;
    }
  }

  /** Binary Search */
  public int binaryFind(long searchKey) {
    int lowerBound = 0;
    int upperBound = nElems - 1;
    int curIn;
    while (true) {
      curIn = (lowerBound + upperBound) / 2;
      if (a[curIn] == searchKey) {
        return curIn;
      } else if (lowerBound > upperBound) {
        return nElems;
      } else {
        if (a[curIn] < searchKey) {
          lowerBound = curIn + 1;
        } else {
          upperBound = curIn - 1;
        }
      }
    }
  }

  public void insert(long value) {
    a[nElems] = value;
    nElems++;
  }

  public boolean delete(long value) {
    int j;
    for (j = 0; j < nElems; j++) {
      if (value == a[j]) {
        break;
      }
    }
    if (j == nElems) {
      return false;
    } else {
      for (int k = j; k < nElems; k++) {
        a[k] = a[k + 1]; // override with the next element
      }
      nElems--;
      return true;
    }
  }

  public void display() {
    for (int j = 0; j < nElems; j++) {
      System.out.print(a[j] + " ");
    }
    System.out.println();
  }
}
