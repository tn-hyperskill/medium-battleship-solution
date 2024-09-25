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
