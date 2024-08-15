package co.loyyee.misc;
///usr/bin/env jbang "$0" "$@" ; exit $?
/** Helper functions  */
public class Helper {
  private Helper() {}
  static public  void pprint( int[] arr ) {
    for(int el : arr ) {
      System.out.println(el);
    }
  }
}
