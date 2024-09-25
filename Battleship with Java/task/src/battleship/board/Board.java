package battleship.board;

import battleship.cell.Cell;
import battleship.util.ImmutBiIdxTable;
import battleship.util.matrix.Matrix;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

  // Constants
  /**
   * <h6>Row Enumeration</h6>
   */
  static final ImmutBiIdxTable<Integer> COL_ENUMERATOR = ImmutBiIdxTable
      .fromSeq(IntStream.rangeClosed(1, 10).mapToObj(i -> i));
  /**
   * <h6>Column Enumeration</h6>
   */
  static final ImmutBiIdxTable<Character> ROW_ENUMERATOR =
      ImmutBiIdxTable
          .fromSeq(IntStream.range('A', 'A' + 10).mapToObj(i -> (char) i));

  // Instance fields

  protected final Matrix<Cell> cellsMtx;

  // CRUD-C

  public static Board empty() {
    return new Board();
  }

  protected Board() {
    this(new Matrix(Cell.fog(), HEIGHT(), WIDTH()));
  }

  protected Board(Matrix<Cell> cellsMtx) {
    this.cellsMtx = cellsMtx;
    assert cellsMtx.width() == WIDTH()
        && cellsMtx.height() == HEIGHT();
  }

  // CRUD-R: Getters

  public static int WIDTH() {
    return COL_ENUMERATOR.size();
  }

  public static int HEIGHT() {
    return ROW_ENUMERATOR.size();
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

    Iterator<Character> letterIter = ROW_ENUMERATOR.valIter();
    for (var cellIter = this.cellsMtx.rowWiseIter(); cellIter.hasNext(); ) {
      out.append('\n');
      out.append(letterIter.next());
      for (int col = 0; col < this.width(); col++) {
        var cell = cellIter.next();
        out.append(" " + cell);
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
