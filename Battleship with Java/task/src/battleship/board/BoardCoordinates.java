package battleship.board;

import battleship.util.matrix.MatrixCoordinates;
import java.text.ParseException;

public final class BoardCoordinates extends MatrixCoordinates {

  // CRUD-C: Common constructors
  BoardCoordinates(int row, int column){
    super(row, column);
  }

  public static BoardCoordinates parse(String humanNotation)
      throws ParseException {
    if (humanNotation.length() != 2 && humanNotation.length() != 3) {
      throw new IllegalArgumentException("string length should equal 2 or 3");
    }

    final int colIdx, rowIdx;
    try {
      colIdx = Board.COL_ENUMERATOR.getIdx(humanNotation.charAt(0));
    } catch (Exception e) {
      throw (ParseException) new ParseException("", 0).initCause(e);
    }
    try {
      rowIdx = Board.ROW_ENUMERATOR.getIdx(
          Integer.parseInt(humanNotation.substring(1)));
    } catch (Exception e) {
      throw (ParseException) new ParseException("", 1).initCause(e);
    }

    return new BoardCoordinatesBuilder().row(rowIdx).column(colIdx).build();
  }

  // CRUD-C: Builder (Lite) Pattern
  @Override
  public BoardCoordinates cloneWithRow(int $row) {
    return this.toBuilder().row($row).build();
  }
  @Override
  public BoardCoordinates cloneWithCol(int $col) {
    return this.toBuilder().column($col).build();
  }

  // CRUD-R: Factory methods
  public static BoardCoordinatesBuilder builder(){
    return new BoardCoordinatesBuilder();
  }

  public BoardCoordinatesBuilder toBuilder(){
    return builder().row(this.row).column(this.col);
  }

  // CRUD-R: Properties

  @Override public int hashCode() {
    return this.col * Board.HEIGHT() + this.row;
  }

  public String displayForUser(){
    return Board.COL_ENUMERATOR.getVal(this.col).toString()
        + Board.ROW_ENUMERATOR.getVal(this.row);
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
