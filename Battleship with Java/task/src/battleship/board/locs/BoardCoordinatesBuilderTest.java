package battleship.board.locs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import battleship.util.matrix.exceptions.ColIndexOutOfMatrixBounds;
import battleship.util.matrix.exceptions.RowIndexOutOfMatrixBounds;
import org.junit.jupiter.api.Test;

class BoardCoordinatesBuilderTest {

  @Test void buildZeroCoordinates() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    BoardCoordinates coord = builder.row(0).column(0).build();
    assertEquals(0, coord.row);
    assertEquals(0, coord.col);
  }

  @Test void buildEqualCoordinates() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    BoardCoordinates coord = builder.row(9).column(9).build();
    assertEquals(9, coord.row);
    assertEquals(9, coord.col);
  }

  @Test void buildNonEqualCoordinates() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    BoardCoordinates coord = builder.row(9).column(5).build();
    assertEquals(9, coord.row);
    assertEquals(5, coord.col);
  }

  @Test void rowThrowsExceptionForInvalidRowIndex() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(RowIndexOutOfMatrixBounds.class, () -> builder.row(100));
  }

  @Test void columnThrowsExceptionForInvalidColumnIndex() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(ColIndexOutOfMatrixBounds.class, () -> builder.column(100));
  }

  @Test void buildThrowsExceptionIfRowIsNotSet() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(RuntimeException.class, builder::build);
  }

  @Test void buildThrowsExceptionIfColumnIsNotSet() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(RuntimeException.class, builder::build);
  }

  @Test void buildThrowsExceptionIfBothFieldsAreNotSet() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(RuntimeException.class, builder::build);
  }
}
