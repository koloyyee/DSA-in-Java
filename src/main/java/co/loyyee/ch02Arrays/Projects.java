package co.loyyee.ch02Arrays;
///usr/bin/env jbang "$0" "$@" ; exit $?

import java.util.Arrays;

public class Projects {

    public static void main(String... args) {

        var ha = new HighArray(10);
        ha.insert(3);
        ha.insert(1);
        ha.insert(5);
        ha.insert(9);

        // ha.removeMax();
        // ha.sort();

        var oa = new OrdArray(10);
        oa.insert(1);
        oa.insert(2);
        oa.insert(3);
        oa.insert(4);
        oa.insert(4);
        // var idx = oa.findBin(9);
        // System.out.println(idx);
        // oa.insertBin(10);
        // oa.display();
        // oa.deleteBin(10);
        // oa.display();
        // var newLongs = new long[] { 5, 6, 7, 8 };
        // oa.merge(newLongs);
        oa.noDups();
    }
}

class HighArray {
    private long[] a; // ref to array a
    private int nElems; // number of data items

    public HighArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    // project 2.1
    public long getMax() {
        long max = Long.MIN_VALUE;
        // int key = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                // key = i;
            }
        }
        if (max == Long.MIN_VALUE) {
            return -1;
        } else {
            return max;
        }
    }

    // 2.2 similar to pop
    public long removeMax() {
        long max = getMax();
        delete(max);
        return max;
    }

    // 2.3 sort

    public long[] sort() {
        long[] sortedLong = new long[a.length];
        int sortCount = 0;

        while (nElems > 0) {
            var max = removeMax();
            sortedLong[sortCount++] = max;
        }
        nElems = sortCount; // reset the count
        // System.out.println("sorted long: "); // sanity check
        // Arrays.stream(sortedLong).forEach(System.out::println);
        return sortedLong;
    }

    public long getMin() {
        long max = Long.MIN_VALUE;
        for (long el : a) {
            if (el > max) {
                max = el;
            }
        }
        return max;
    }

    public long removeMin() {
        long min = getMin();
        delete(min);
        return min;
    }

    public boolean find(long searchKey) {
        int j; // for each element,

        for (j = 0; j < nElems; j++) {
            if (a[j] == searchKey)
                break;
        }
        if (j == nElems) // gone to end?
            return false; // yes, can’t find it
        else
            return true; // no, found it
        // end f
    }

    public void insert(long value) // put element into array
    { // insert it
        a[nElems] = value;
        nElems++; // increment size
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
                a[k] = a[k + 1];
                nElems--;
                return true;
            }
        }
        return false;
    }

    public void display() {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "");
            System.out.println("");

        }
    }
}

class OrdArray {
    private long[] a; // ref to array a
    private int nElems; // number of data items
    // ----------------------------------------------------------

    public OrdArray(int max) {
        a = new long[max];
        nElems = 0;

    } // ----------------------------------------------------------

    public int size() {
        return nElems;
    }

    // ----------------------------------------------------------
    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;
        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey)
                return curIn; // found it
            else if (lowerBound > upperBound)
                return nElems; // can’t find it
            else // divide range
            {
                if (a[curIn] < searchKey)
                    lowerBound = curIn + 1; // it’s in upper half
                else
                    upperBound = curIn - 1; // it’s in lower half
            } // end else divide range
        } // end while
    } // end find()

    // 2.4, return index
    public int findBin(long searchKey) {
        return findBin(0, nElems, searchKey);
    }

    public int findBin(int lo, int hi, long searchKey) {
        if (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == searchKey) {
                return mid;
            }
            if (a[mid] > searchKey) {
                return findBin(lo, mid + 1, searchKey);
            }
            if (a[mid] < searchKey) {
                return findBin(mid + 1, hi, searchKey);
            }

        }
        return -1;
    }

    // 2.5 merge
    public void merge(long[] newLongs) {
        long[] merged = new long[a.length + newLongs.length];
        int i;
        for (i = 0; i < nElems; i++) {
            merged[i] = a[i];
        }
        for (int j = 0; j < newLongs.length; j++) {
            merged[i++] = newLongs[j];
        }
        a = merged;
        Arrays.stream(a).forEach(System.out::println);
    }

    // 2.6 no duplicates
    public void noDups() {
        long[] existed = new long[nElems];
        int newSize = 0;

        for (int i = 0; i < nElems; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < newSize; j++) {
                if (a[i] == existed[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                existed[newSize++] = a[i];
            }
        }

        a = existed;
        nElems = newSize;
    }

    public void insert(long value) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j] > value)
                break;
        }
        for (int k = nElems; k > j; k--) {
            // put element into array
            // find where it goes // (linear search)
            // move bigger ones up
            a[k] = a[k - 1];
        }
        a[j] = value;
        // insert it // increment size
        nElems++;
    } // end i

    public boolean delete(long value) {
        int j = find(value);
        if (j == nElems) { // can’t find it
            return false;
        } else // found it
        {
            for (int k = j; k < nElems; k++) { // move bigger ones down
                a[k] = a[k + 1];
                nElems--;
                return true;
            }
        }
        return false;
    }

    public void display() {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "");
            System.out.println("");

        }
    }
}
