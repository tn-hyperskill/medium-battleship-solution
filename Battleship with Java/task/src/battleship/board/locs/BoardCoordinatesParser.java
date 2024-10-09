package battleship.board.locs;

import battleship.board.Board;
import java.text.ParseException;

final class BoardCoordinatesParser {

  // Constants – Allowed input lengths

  private static final int MIN_STR_LEN = 2;
  private static final int MAX_STR_LEN = 3;

  // Constants – Indexes of codes

  private static final int STR_IDX_OF_ROW_CODE = 0;
  private static final int STR_IDX_OF_COL_CODE = 1;

  // CRUD-C

  private BoardCoordinatesParser() {
  }

  // CRUD-R

  public static BoardCoordinates parse(String humanNotation)
      throws ParseException {
    if (humanNotation.length() != MIN_STR_LEN
        && humanNotation.length() != MAX_STR_LEN) {
      throw new IllegalArgumentException("string length should equal 2 or 3");
    }

    final int rowIdx, colIdx;
    try {
      rowIdx = Board.ROW_ENUMERATOR.getIdx(humanNotation.charAt(
          STR_IDX_OF_ROW_CODE));
    } catch (Exception e) {
      throw (ParseException) new ParseException("",
          STR_IDX_OF_ROW_CODE).initCause(e);
    }
    try {
      colIdx = Board.COL_ENUMERATOR.getIdx(
          Integer.parseInt(humanNotation.substring(STR_IDX_OF_COL_CODE)));
    } catch (Exception e) {
      throw (ParseException) new ParseException("",
          STR_IDX_OF_COL_CODE).initCause(e);
    }

    return new BoardCoordinatesBuilder().row(rowIdx).column(colIdx).build();
  }
}
