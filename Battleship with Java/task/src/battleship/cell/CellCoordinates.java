package battleship.cell;

import battleship.board.Board;
import battleship.util.matrix.MatrixCoordinates;
import java.text.ParseException;

public final class CellCoordinates extends MatrixCoordinates {

  // CRUD-C: Common constructors
  CellCoordinates(int row, int column){
    super(row, column);
  }

  public static CellCoordinates parse(String humanNotation)
      throws ParseException {
    if (humanNotation.length() != 2 && humanNotation.length() != 3) {
      throw new IllegalArgumentException("string length should equal 2 or 3");
    }

    final int colIdx, rowIdx;
    try {
      colIdx = Board.COL_ENUMERATOR.findIdx(humanNotation.charAt(0));
    } catch (Exception e) {
      throw (ParseException) new ParseException("", 0).initCause(e);
    }
    try {
      rowIdx = Board.ROW_ENUMERATOR.findIdx(
          Integer.parseInt(humanNotation.substring(1)));
    } catch (Exception e) {
      throw (ParseException) new ParseException("", 1).initCause(e);
    }

    return new CellCoordinatesBuilder().row(rowIdx).column(colIdx).build();
  }

  // CRUD-C: Builder (Lite) Pattern
  @Override
  public CellCoordinates cloneWithRow(int $row) {
    return CellCoordinates.builder().row($row).column(this.col).build();
  }
  @Override
  public CellCoordinates cloneWithCol(int $col) {
    return CellCoordinates.builder().row(this.row).column($col).build();
  }

  public static CellCoordinatesBuilder builder(){
    return new CellCoordinatesBuilder();
  }

  @Override public int hashCode() {
    return this.col * Board.HEIGHT() + this.row;
  }

  // CRUD-R: Sexual methods

  public CellCoordinates mergeToMinimizeCords(CellCoordinates lover) {
    var child = new CellCoordinatesBuilder().row(Math.min(this.col, lover.col))
        .column(Math.min(this.row, lover.row)).build();
    return child;
  }

  public CellCoordinates mergeToMaximizeCords(CellCoordinates lover) {
    var child = new CellCoordinatesBuilder().row(Math.max(this.col, lover.col))
        .column(Math.max(this.row, lover.row)).build();
    return child;
  }

}
