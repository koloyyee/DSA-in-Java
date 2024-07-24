package co.loyyee.ch02Arrays;

import co.loyyee.own.BinarySearch;

public class App {
	public static void main(String[] args){
		
	}
	public static void runHA() {
		var ha = new HighArray(100);
		ha.insert(77);
		ha.insert(99);
		ha.insert(44);
		ha.insert(55);
		ha.insert(22);
		ha.insert(88);
		ha.insert(11);
		ha.insert(00);
		
		ha.display();
		int searchKey = 35;
		if(ha.find(searchKey)) {
			System.out.println("Found " + searchKey);
		} else {
			System.out.println("Can't find " + searchKey);
		}
		ha.delete(00);
		ha.delete(55);
		ha.delete(99);
		
		ha.display();
		
		
	}
	public static void binarySearch() {
		
		long[] arr;
		arr = new long[10];
		// create a sorted array
		arr[0] = 00;
		arr[1] = 11;
		arr[2] = 22;
		arr[3] = 33;
		arr[4] = 44;
		arr[5] = 55;
		arr[6] = 66;
		arr[7] = 77;
		arr[8] = 88;
		arr[9] = 99;
		
		var bs = new BinarySearch();
		int target = bs.find(33, arr);
		System.out.println(target);
	}
	
}
