package battleship.board.locs;

import battleship.board.Board;
import battleship.util.matrix.MatrixCoordinates;
import java.text.ParseException;

public final class BoardCoordinates extends MatrixCoordinates {

  // CRUD-C: Common constructors

  BoardCoordinates(int row, int column) {
    super(row, column);
  }

  public static BoardCoordinates valueOf(MatrixCoordinates valToValidate) {
    return BoardCoordinates.builder()
        .row(valToValidate.row).column(valToValidate.col)
        .build();
  }

  public static BoardCoordinates parse(String humanNotation)
      throws ParseException {
    return BoardCoordinatesParser.parse(humanNotation);
  }


  // CRUD-C: Builder [Lite] Pattern
  @Override
  public BoardCoordinates cloneWithRow(int $row) {
    return this.toBuilder().row($row).build();
  }

  @Override
  public BoardCoordinates cloneWithCol(int $col) {
    return this.toBuilder().column($col).build();
  }

  // CRUD-C: Pure arithmetic operations

  /**
   * Attempts to decrement both the row and column by 1. If decrementing would
   * result in a coordinate that is out of the board's valid range, that
   * coordinate will remain unchanged.
   * <p>
   * Note that the row and column are handled independently, meaning that one
   * coordinate might be decremented while the other is unchanged.
   *
   * @return A new {@code BoardCoordinates} with the decremented row and column
   * values if within board boundaries.
   */
  public BoardCoordinates saturatingDecrement() {
    var out = this.toBuilder();
    out.tryMapColumn(i -> i.get() - 1)  // Decrement column if valid
        .tryMapRow(i -> i.get() - 1);   // Decrement row if valid
    return out.build();
  }

  /**
   * Attempts to increment both the row and column by 1. If incrementing would
   * result in a coordinate that is out of the board's valid range, that
   * coordinate will remain unchanged.
   * <p>
   * Note that the row and column are handled independently, meaning that one
   * coordinate might be incremented while the other is unchanged.
   *
   * @return A new {@code BoardCoordinates} with the incremented row and column
   * values if within board boundaries.
   */
  public BoardCoordinates saturatingIncrement() {
    var out = this.toBuilder();
    out.tryMapColumn(i -> i.get() + 1)  // Increment column if valid
        .tryMapRow(i -> i.get() + 1);   // Increment row if valid
    return out.build();
  }

  // CRUD-R: Factory methods of external types

  public static BoardCoordinatesBuilder builder() {
    return new BoardCoordinatesBuilder();
  }

  @Override public BoardCoordinatesBuilder toBuilder() {
    return builder().row(this.row).column(this.col);
  }

  /**
   * Converts to direct superclass, cutting of all the subclass-specific
   * properties.
   */
  public MatrixCoordinates sliceObj() {
    return MatrixCoordinates.builder()
        .column(this.col).row(this.row)
        .build();
  }

  // CRUD-R: Properties

  @Override public boolean equals(Object o) {
    // `super`s impl. is good, but `checkstyle` wants an override.
    // It also helps when jumping using "go to definition" editor action.
    return super.equals(o);
  }

  @Override public int hashCode() {
    return this.col * Board.height() + this.row;
  }

  public String displayForUser() {
    return Board.ROW_ENUMERATOR.getVal(this.row).toString()
        + Board.COL_ENUMERATOR.getVal(this.col);
  }

  // CRUD-R: Sexual methods

  public BoardCoordinates mergeToMinimizeCords(BoardCoordinates lover) {
    // Minimize row and column
    return new BoardCoordinatesBuilder()
        .row(Math.min(this.row, lover.row))
        .column(Math.min(this.col, lover.col))
        .build();
  }

  public BoardCoordinates mergeToMaximizeCords(BoardCoordinates lover) {
    // Maximize row and column
    return new BoardCoordinatesBuilder()
        .row(Math.max(this.row, lover.row))
        .column(Math.max(this.col, lover.col))
        .build();
  }

}
