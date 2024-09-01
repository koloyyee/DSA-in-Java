package co.loyyee.ch06Recursion;

/**
 * Pythagorians Triangles
 *
 * <p>e.g: <br>
 * x <br>
 * x x <br>
 * x x x <br>
 * 3 rows <br>
 * sum is 6
 *
 * <p>Xn = n ( n + 1) / 2
 */
public class BasicRecursion{

  public static void main(String[] args) {
    int n = 5;
    var triRes = triangle(n);

    // Xn  = n ( n + 1) / 2
    var expectedSum = n * (n + 1) / 2;
    System.out.println("Sum: " + expectedSum);
    assert(expectedSum == triRes );
   
    var expectedFact = 24;
    var actual = factorial(4);
    assert(expectedFact == actual );
  }

  public static int triangle(int n) {
    //		for(int i = 0; i < n ; i++) {
    //			System.out.print("x");
    //		}
    //		System.out.println();
    System.out.println("Entering: n= " + n);
    if (n == 1) {
      return 1;
    }

    var val = n + triangle(n - 1);
    System.out.println("Returning " + val);
    return val;
  }
  
  public static int factorial(int n ) {
    System.out.println("n: " + n);
    if ( n == 0 )  {
      return 1;
    }
    var val = n * factorial(n -1 );
    System.out.println("Returning: " + val );
    return val;
  }
}
