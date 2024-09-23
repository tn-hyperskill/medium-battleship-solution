package battleship.board;

import battleship.cell.CellCoordinates;
import battleship.ship.Ship;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <h6>Protagonist's Board</h6>
 */
public final class ProtagoBoard {

  // Instance's fields
  private final Board innerBoard = Board.empty();
  private final List<Ship> aliveShips = new ArrayList();
  private final Map<CellCoordinates, Ship> hitBoxes = new HashMap<>();
  private final Set<CellCoordinates> ctrlZones = new HashSet<>();

  // CRUD-C
  public static ProtagoBoard empty() {
    return new ProtagoBoard();
  }

  protected ProtagoBoard() {
  }

  // CRUD-U
  public void placeShip(Ship incomingShip){
    // Check if the incomingShip can be placed
    if (incomingShip.hitBoxesStream().anyMatch(this.ctrlZones::contains)){
      throw new IllegalArgumentException(
          "an incoming ship can't be placed on an existing control zone"
      );
    }
    // Put every incomingShip part or dependency.
    this.aliveShips.add(incomingShip);
    incomingShip.hitBoxesStream().forEach(hitBox -> this.hitBoxes.put(hitBox, incomingShip));
    incomingShip.ctrlZonesStream().forEach(ctrlZone -> this.ctrlZones.add(ctrlZone));
  }
}
