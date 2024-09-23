package battleship.board;

import battleship.cell.CellMatrix;
import battleship.util.ImmutBiIdxTable;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Board {

  // Constants

  /**
   * <h6></h6>
   */
  public static final ImmutBiIdxTable<Integer> ROW_ENUMERATOR = ImmutBiIdxTable
      .fromSeq(IntStream.rangeClosed(1, 10).mapToObj(i -> i));
  /**
   * <h6>Column Enumeration</h6>
   */
  public static final ImmutBiIdxTable<Character> COL_ENUMERATOR =
      ImmutBiIdxTable
          .fromSeq(IntStream.range('A', 'J').mapToObj(i -> (char) i));

  // Instance fields

  private final CellMatrix cellsMtx = new CellMatrix(WIDTH(), HEIGHT());

  // CRUD-C

  public static Board empty() {
    return new Board();
  }

  protected Board() {
  }

  // CRUD-R: Getters

  public static int WIDTH() {
    return ROW_ENUMERATOR.size();
  }

  public static int HEIGHT() {
    return COL_ENUMERATOR.size();
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

    Iterator<Character> letterIter = COL_ENUMERATOR.valIter();
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
    return "  " + ROW_ENUMERATOR
        .valStream()
        .map(Object::toString)
        .collect(Collectors.joining(" "));
  }
}
