package battleship.board;

import java.text.ParseException;
import java.util.NoSuchElementException;

public final class Coordinate {

  // Instance fields
  public final int row, col;

  // CRUD-C

  public Coordinate(int row, int column) {
    if (!Board.ROW_ENUMERATION.hasIdx(row)){
      throw new IllegalArgumentException("nonexistent row index");
    }
    if (!Board.COL_ENUMERATION.hasIdx(column)){
      throw new IllegalArgumentException("nonexistent column index");
    }
    this.row = row;
    this.col = column;
  }

  public static Coordinate parse(String humanNotation) throws ParseException {
    if (humanNotation.length() != 2 && humanNotation.length() != 3){
      throw new IllegalArgumentException("string length should equal 2 or 3");
    }

    final int colIdx, rowIdx;
    try {
      colIdx = Board.COL_ENUMERATION.findIdx(humanNotation.charAt(0));
    }catch (Exception e){
      throw (ParseException) new ParseException("", 0).initCause(e);
    }
    try {
      rowIdx = Board.ROW_ENUMERATION.findVal(Integer.parseInt(
          humanNotation.substring(1)));
    }catch (Exception e){
      throw (ParseException) new ParseException("", 1).initCause(e);
    }

    return new Coordinate(rowIdx, colIdx);
  }

  // CRUD-R: Properties
  @Override
  public boolean equals(Object rhs){
    return (rhs instanceof Coordinate brother)
        && (this.row == brother.row) && (this.col == brother.col);
  }

  @Override public int hashCode() {
    return this.col * Board.HEIGHT() + this.row;
  }
}
