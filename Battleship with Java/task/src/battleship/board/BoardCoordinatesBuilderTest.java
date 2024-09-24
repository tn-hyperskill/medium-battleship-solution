package battleship.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class BoardCoordinatesBuilderTest {

  @Test
  void buildCreatesCorrectBoardCoordinates() {
    BoardCoordinatesBuilder builder;
    BoardCoordinates coord;

    builder = new BoardCoordinatesBuilder();
    coord = builder.row(0).column(0).build();
    assertEquals(0, coord.row);
    assertEquals(0, coord.col);

    builder = new BoardCoordinatesBuilder();
    coord = builder.row(9).column(5).build();
    assertEquals(9, coord.row);
    assertEquals(5, coord.col);

    builder = new BoardCoordinatesBuilder();
    coord = builder.row(9).column(9).build();
    assertEquals(9, coord.row);
    assertEquals(9, coord.col);
  }

  @Test
  void rowThrowsExceptionForInvalidRowIndex() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(IllegalArgumentException.class, () -> builder.row(100));
  }

  @Test
  void columnThrowsExceptionForInvalidColumnIndex() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(IllegalArgumentException.class, () -> builder.column(100));
  }

  @Test
  void buildThrowsExceptionIfRowIsNotSet() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(RuntimeException.class, builder::build);
  }

  @Test
  void buildThrowsExceptionIfColumnIsNotSet() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(RuntimeException.class, builder::build);
  }

  @Test
  void buildThrowsExceptionIfBothFieldsAreNotSet() {
    BoardCoordinatesBuilder builder = new BoardCoordinatesBuilder();
    assertThrows(RuntimeException.class, builder::build);
  }
}
