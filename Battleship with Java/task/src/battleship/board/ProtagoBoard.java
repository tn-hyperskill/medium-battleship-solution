package battleship.board;

import battleship.cell.CellCoordinates;
import battleship.cell.ShipCell;
import battleship.ship.AnchoredShip;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h6>Protagonist's Board</h6>
 */
public final class ProtagoBoard extends Board{

  // Instance's fields
  private final List<AnchoredShip> aliveShips = new ArrayList();
  private final Set<CellCoordinates> ctrlZones = new HashSet<>();

  // CRUD-C
  public static ProtagoBoard empty() {
    return new ProtagoBoard();
  }

  protected ProtagoBoard() {
    super();
  }

  // CRUD-U
  public void placeShip(AnchoredShip incomingShip){
    // Check if the incomingShip can be placed
    if (incomingShip.hitBoxesStream().anyMatch(this.ctrlZones::contains)){
      throw new IllegalArgumentException(
          "an incoming ship can't be placed on an existing control zone"
      );
    }
    // Put every incomingShip part or dependency.
    this.aliveShips.add(incomingShip);
    incomingShip.hitBoxesStream().forEach(hitBox -> this.cellsMtx.setValAt(
        hitBox, new ShipCell(incomingShip)));
    incomingShip.ctrlZonesStream().forEach(ctrlZone -> this.ctrlZones.add(ctrlZone));
  }
}
