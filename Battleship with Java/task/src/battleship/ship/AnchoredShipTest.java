package battleship.ship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import battleship.board.BoardCoordinates;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class AnchoredShipTest {

  @Test
  void constructorCreatesCorrectShip() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(3).build();
    AnchoredShip ship = new AnchoredShip(ShipModel.BATTLESHIP, start, finish);
    assertEquals(ShipModel.BATTLESHIP, ship.model());
    assertEquals(4, ship.hpLeft());
  }

  @Test
  void constructorThrowsExceptionForFatShip() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(3).column(3).build();
    assertThrows(IllegalArgumentException.class, () -> new AnchoredShip(ShipModel.BATTLESHIP, start, finish));
  }

  @Test
  void constructorWithExpectedSizeCreatesShipCorrectly() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(2).build();
    var expectedModel = ShipModel.CRUISER;
    var ship = new AnchoredShip(ShipModel.CRUISER, start, finish);
    assertEquals(expectedModel, ship.model());
  }

  @Test
  void calcVolumeReturnsCorrectValue() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(3).build();
    AnchoredShip ship = new AnchoredShip(ShipModel.BATTLESHIP, start, finish);
    assertEquals(4, ship.volume());
  }

  @Test
  void hitBoxesStreamReturnsCorrectStream() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(2).build();
    AnchoredShip ship = new AnchoredShip(ShipModel.SUBMARINE, start, finish);
    Stream<BoardCoordinates> hitBoxes = ship.hitBoxesStream();
    assertEquals(3, hitBoxes.count());
  }

  @Test
  void hitBoxesIterReturnsCorrectIterator() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(2).build();
    AnchoredShip ship = new AnchoredShip(ShipModel.SUBMARINE, start, finish);
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
    AnchoredShip ship = new AnchoredShip(ShipModel.DESTROYER, start, finish);
    Iterator<BoardCoordinates> iter = ship.hitBoxesIter();

    iter.next(); // first element
    iter.next(); // second element
    assertThrows(NoSuchElementException.class, iter::next);
  }

  @Test
  void maxCellCordsReturnsMaxCoordinates() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(0).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(2).column(0).build();
    AnchoredShip ship = new AnchoredShip(ShipModel.CRUISER, start, finish);
    assertEquals(finish, ship.maxCellCords());
  }

  @Test
  void minCellCordsReturnsMinCoordinates() {
    BoardCoordinates start = BoardCoordinates.builder().row(0).column(3).build();
    BoardCoordinates finish = BoardCoordinates.builder().row(0).column(0).build();
    AnchoredShip ship = new AnchoredShip(ShipModel.BATTLESHIP, start, finish);
    assertEquals(finish, ship.minCellCords());
  }
}
