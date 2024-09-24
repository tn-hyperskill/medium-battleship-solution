package battleship.ship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import battleship.board.BoardCoordinates;
import battleship.util.Converter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class AnchoredShipTest {

  @Test
  void constructorCreatesShipWithCorrectSizeAndHp() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(3).build();
    AnchoredShip ship = new AnchoredShip(start, finish);
    assertEquals(ShipSize.fromVolume(4), ship.size);
    assertEquals(4, ship.hpLeft());
  }

  @Test
  void constructorThrowsExceptionForFatShip() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(3).column(3).build();
    assertThrows(IllegalArgumentException.class, () -> new AnchoredShip(start, finish));
  }

  @Test
  void constructorWithExpectedSizeCreatesShipCorrectly() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(2).build();
    ShipSize expectedSize = ShipSize.fromVolume(3);
    AnchoredShip ship = new AnchoredShip(start, finish, expectedSize);
    assertEquals(expectedSize, ship.size);
  }

  @Test
  void calcVolumeReturnsCorrectValue() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(3).build();
    AnchoredShip ship = new AnchoredShip(start, finish);
    assertEquals(4, ship.volume());
  }

  @Test
  void hitBoxesStreamReturnsCorrectStream() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(2).build();
    AnchoredShip ship = new AnchoredShip(start, finish);
    Stream<BoardCoordinates> hitBoxes = ship.hitBoxesStream();
    assertEquals(3, hitBoxes.count());
  }

  @Test
  void hitBoxesIterReturnsCorrectIterator() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(2).build();
    AnchoredShip ship = new AnchoredShip(start, finish);
    Iterator<BoardCoordinates> iter = ship.hitBoxesIter();

    assertTrue(iter.hasNext());
    assertEquals(start, iter.next());
    assertEquals(start.cloneWithCol(1), iter.next());
    assertEquals(start.cloneWithCol(2), iter.next());
    assertFalse(iter.hasNext());
  }

  @Test
  void hitBoxesIterThrowsExceptionWhenNoMoreElements() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(1).build();
    AnchoredShip ship = new AnchoredShip(start, finish);
    Iterator<BoardCoordinates> iter = ship.hitBoxesIter();

    iter.next(); // first element
    iter.next(); // second element
    assertThrows(NoSuchElementException.class, iter::next);
  }

  @Test
  void maxCellCordsReturnsMaxCoordinates() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(2).column(0).build();
    AnchoredShip ship = new AnchoredShip(start, finish);
    assertEquals(finish, ship.maxCellCords());
  }

  @Test
  void minCellCordsReturnsMinCoordinates() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(3).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(0).build();
    AnchoredShip ship = new AnchoredShip(start, finish);
    assertEquals(finish, ship.minCellCords());
  }
}
