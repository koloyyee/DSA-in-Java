package co.loyyee.own;

public class BinarySearch {
	
	public int find(long target, long[] arr) {
		int lo, hi, mid;
		lo = 0;
		hi =  arr.length - 1;
		while(true) {
			mid = lo + ( hi - lo ) /2;
		if(arr[mid] == target) {
			return mid;
		} else if (lo> hi) {
			return -1;
		}
			if( arr[mid] < target ) {
				lo = mid + 1;
			} else {
				hi = mid -1;
			}
		}
	}
}
