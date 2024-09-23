package battleship.board;

import battleship.ship.Ship;
import java.util.HashMap;
import java.util.Map;

/**
 * <h6>Protagonist's Board</h6>
 */
public final class ProtagoBoard {

  // Instance's fields
  private final Board innerBoard = Board.empty();
  private final Map<Coordinate, Ship> ships = new HashMap<>();

  // CRUD-C
  public static ProtagoBoard empty() {
    return new ProtagoBoard();
  }

  protected ProtagoBoard() {
  }

  // CRUD-U
  public void placeShip(Ship ship){
    // ToDo: register on `ships` && `innerBoard`
  }
}
