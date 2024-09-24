package battleship.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class BoardTest {

  @Test
  void emptyBoardHasCorrectWidthAndHeight() {
    Board board = Board.empty();
    assertEquals(10, board.width());
    assertEquals(10, board.height());
  }

  @Test
  void rowEnumeratorHasCorrectValues() {
    assertEquals(10, Board.ROW_ENUMERATOR.size());
    for (int i = 1; i <= 10; i++) {
      assertTrue(Board.ROW_ENUMERATOR.hasVal(i));
    }
  }

  @Test
  void colEnumeratorHasCorrectValues() {
    assertEquals(10, Board.COL_ENUMERATOR.size());
    for (char c = 'A'; c <= 'J'; c++) {
      assertTrue(Board.COL_ENUMERATOR.hasVal(c));
    }
  }

  @Test
  void toStringReturnsCorrectEmptyBoardString() {
    Board board = Board.empty();
    String expected = """
          1 2 3 4 5 6 7 8 9 10
        A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        """;
    assertEquals(expected.trim(), board.toString().trim());
  }
}
