package co.loyyee.ch06Recursion;

/**
 * eat, ate, tea
 * */
public class Anagram {
	static int size;
	static int count;
	static char[] arrChar;
	
	
	public static void main(String[] args){
	String word = "cats";
	arrChar = word.toCharArray();
	size = arrChar.length;
	doAnagram(size);
	}
	static void doAnagram(int newSize ) {
		if(newSize == 1 ) {
			return;
		}
		for(int i = 0; i < newSize; i++) {
			doAnagram(newSize - 1);
			if(newSize == 2 )	 {
				displayWord();
			}
			rotate(newSize);
		}
	}
	// rotate left all chars from position to end;
	static void rotate( int newSize) {
		int i;
		int position = size - newSize;
		char temp = arrChar[position]; // save first letter
		for(i = position + 1 ; i  < size; i++ ) { // shift others to the left
			arrChar[i - 1 ] = arrChar[i];
		}
		arrChar[i - 1 ] = temp; // put the first on right
	}
	static void displayWord() {
		if(count < 99) {
      System.out.print(" ");
		}
		if(count < 9) {
			System.out.print(" ");
		}
    System.out.print(++count +  " ");
		for(int i = 0; i < size ; i++) {
      System.out.print(arrChar[i]);
		}
    System.out.flush();
		if( count % 6 == 0 ) {
      System.out.println(" ");
		}
	}
}
