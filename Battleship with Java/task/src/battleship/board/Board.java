package battleship.board;

import battleship.cell.Cell;
import battleship.util.ImmutBiIdxTable;
import battleship.util.matrix.Matrix;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

  // Constants

  private static final short DIMENSION_SIZE = 10;

  /**
   * <h6>Row Enumeration</h6>
   */
  public static final ImmutBiIdxTable<Integer> COL_ENUMERATOR = ImmutBiIdxTable
      .fromSeq(IntStream.rangeClosed(1, DIMENSION_SIZE).boxed());
  /**
   * <h6>Column Enumeration</h6>
   */
  public static final ImmutBiIdxTable<Character> ROW_ENUMERATOR =
      ImmutBiIdxTable
          .fromSeq(IntStream.range('A', 'A' + DIMENSION_SIZE)
              .mapToObj(i -> (char) i));

  // Instance fields

  protected final Matrix<Cell> cellsMtx;

  // CRUD-C

  /**
   * @return construct an empty (foggy) board for unit tests.
   */
  static Board foggy() {
    return new Board(Cell.fog());
  }

  protected Board(Cell defaultCell) {
    this(new Matrix<>(defaultCell, height(), width()));
  }

  protected Board(Matrix<Cell> cellsMtx) {
    this.cellsMtx = cellsMtx;
    assert cellsMtx.width() == width()
        && cellsMtx.height() == height();
  }

  // CRUD-R: Getters

  public static int width() {
    return COL_ENUMERATOR.size();
  }

  public static int height() {
    return ROW_ENUMERATOR.size();
  }

  // CRUD-R: Fabrication methods for external types

  @Override
  public final String toString() {
    var out = new StringBuilder(this.drawnIntEnumeration());

    Iterator<Character> letterIter = ROW_ENUMERATOR.valIter();
    for (var cellIter = this.cellsMtx.rowWiseIter(); cellIter.hasNext(); ) {
      out.append('\n');
      out.append(letterIter.next());
      for (int col = 0; col < this.width(); col++) {
        var cell = cellIter.next();
        out.append(" ").append(cell);
      }
    }

    return out.toString();
  }

  private String drawnIntEnumeration() {
    return "  " + COL_ENUMERATOR
        .valStream()
        .map(Object::toString)
        .collect(Collectors.joining(" "));
  }
}
