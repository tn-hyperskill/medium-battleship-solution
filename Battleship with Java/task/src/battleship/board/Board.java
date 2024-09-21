package battleship.board;

import battleship.util.DoublyIndexedTable;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Board {

  // Constants

  static final DoublyIndexedTable<Integer> INT_ENUMERATION = DoublyIndexedTable
      .fromSeq(IntStream.rangeClosed(1, 10).mapToObj(i -> i));
  static final DoublyIndexedTable<Character> LETTER_ENUMERATION =
      DoublyIndexedTable
          .fromSeq(IntStream.range('A', 'J').mapToObj(i -> (char) i));

  // Instance fields

  private CellMatrix cellsMtx = new CellMatrix(WIDTH(), HEIGHT());

  // CRUD-C

  public static Board empty() {
    return new Board();
  }

  protected Board() {
  }

  // CRUD-R: Getters

  public static int WIDTH() {
    return INT_ENUMERATION.size();
  }

  public static int HEIGHT() {
    return LETTER_ENUMERATION.size();
  }

  public int width() {
    return this.cellsMtx.width();
  }

  public int height() {
    return this.cellsMtx.height();
  }

  // CRUD-R: Fabrication methods for external types

  @Override
  public String toString() {
    var out = new StringBuilder(this.drawnIntEnumeration());

    Iterator<Character> letterIter = LETTER_ENUMERATION.valIter();
    for (var cellIter = this.cellsMtx.rowWiseIter(); cellIter.hasNext(); ) {
      out.append('\n');
      out.append(letterIter.next() + " ");
      for (int col = 0; col < this.width(); col++) {
        var cell = cellIter.next();
        out.append(cell + " ");
      }
    }

    return out.toString();
  }

  private String drawnIntEnumeration() {
    return "  " + INT_ENUMERATION
        .valStream()
        .map(Object::toString)
        .collect(Collectors.joining(" "));
  }
}
