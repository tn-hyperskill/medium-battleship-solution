package battleship;

import battleship.util.DoublyIndexedTable;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Board {

  // Constants

  static final DoublyIndexedTable<Integer> INT_ENUMERATION = DoublyIndexedTable
      .fromSeq(IntStream.range(1, 10).mapToObj(i -> i));
  static final DoublyIndexedTable<Character> LETTER_ENUMERATION = DoublyIndexedTable
      .fromSeq(IntStream.range('A', 'J').mapToObj(i -> (char) i));

  // Instance fields

//  private int[] cells =

  // CRUD-R: Const. getters

  public static int WIDTH(){
    return INT_ENUMERATION.size();
  }
  public static int HEIGHT(){
    return LETTER_ENUMERATION.size();
  }


}
