package co.loyyee.ch02Arrays;

public class Arrays {
  public static void main(String[] args) {
    long[] arr;
    arr = new long[100];
    int nElements = 10;
    int j;
    long searchKey;
    // insert 10 random numbers with a loop
    arr[0] = 77;
    arr[1] = 99;
    arr[2] = 48;
    arr[3] = 66;
    arr[4] = 72;
    arr[5] = 88;
    arr[6] = 14;
    arr[7] = 00;
    arr[8] = 12;
    arr[9] = 55;

    for (j = 0; j < 10; j++) {
      System.out.print(arr[j] + " ");
    }
    System.out.println();
    searchKey = 66;
    for (j = 0; j < nElements; j++) {
      if (arr[j] == searchKey) {
        break;
      }
    }
    if (j == nElements) {
      System.out.println("Can't find " + searchKey);
    } else {
      System.out.println("Found " + searchKey);
    }

    searchKey = 88;
    for (j = 0; j < nElements; j++) {
      if (arr[j] == searchKey) {
        break;
      }
    }
    for (int k = j; k < nElements - 1; k++) {
      arr[k] = arr[k + 1];
    }
    
    nElements--;

    for (j = 0; j < nElements; j++) {
      System.out.print(arr[j] + " ");
    }
    System.out.println();
  }
}
