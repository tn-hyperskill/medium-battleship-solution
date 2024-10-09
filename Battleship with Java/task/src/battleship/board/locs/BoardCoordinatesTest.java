package battleship.board.locs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import battleship.board.Board;
import java.text.ParseException;
import org.junit.jupiter.api.Test;

class BoardCoordinatesTest {

  @Test
  void constructorCreatesBoardCoordinatesCorrectly() {
    BoardCoordinates coord = new BoardCoordinates(1, 2);
    assertEquals(1, coord.row);
    assertEquals(2, coord.col);
  }

  @Test
  void parseCreatesBoardCoordinatesFromValidString() throws ParseException {
    BoardCoordinates coord = BoardCoordinates.parse("A1");
    assertEquals(0, coord.row);
    assertEquals(0, coord.col);
  }

  @Test
  void parseThrowsExceptionForInvalidLength() {
    assertThrows(IllegalArgumentException.class,
        () -> BoardCoordinates.parse("A123"));
  }

  @Test
  void parseThrowsParseExceptionForInvalidColumn() {
    assertThrows(ParseException.class, () -> BoardCoordinates.parse("Z1"));
  }

  @Test
  void parseThrowsParseExceptionForInvalidRow() {
    assertThrows(ParseException.class, () -> BoardCoordinates.parse("A11"));
  }

  @Test
  void cloneWithRowReturnsNewCoordinatesWithModifiedRow() {
    BoardCoordinates coord = new BoardCoordinates(1, 2);
    BoardCoordinates clonedCoord = coord.cloneWithRow(3);
    assertEquals(3, clonedCoord.row);
    assertEquals(2, clonedCoord.col);
  }

  @Test
  void cloneWithColReturnsNewCoordinatesWithModifiedCol() {
    BoardCoordinates coord = new BoardCoordinates(1, 2);
    BoardCoordinates clonedCoord = coord.cloneWithCol(3);
    assertEquals(1, clonedCoord.row);
    assertEquals(3, clonedCoord.col);
  }

  @Test
  void mergeToMinimizeCordsReturnsCoordinatesWithMinValues() {
    BoardCoordinates coord1 = new BoardCoordinates(5, 6);
    BoardCoordinates coord2 = new BoardCoordinates(3, 4);
    BoardCoordinates result = coord1.mergeToMinimizeCords(coord2);
    assertEquals(3, result.row);
    assertEquals(4, result.col);
  }

  @Test
  void mergeToMaximizeCordsReturnsCoordinatesWithMaxValues() {
    BoardCoordinates coord1 = new BoardCoordinates(5, 6);
    BoardCoordinates coord2 = new BoardCoordinates(3, 4);
    BoardCoordinates result = coord1.mergeToMaximizeCords(coord2);
    assertEquals(5, result.row);
    assertEquals(6, result.col);
  }

  @Test
  void hashCodeGeneratesCorrectHashCode() {
    BoardCoordinates coord = new BoardCoordinates(1, 2);
    int expectedHashCode = 2 * Board.height() + 1;
    assertEquals(expectedHashCode, coord.hashCode());
  }
}
