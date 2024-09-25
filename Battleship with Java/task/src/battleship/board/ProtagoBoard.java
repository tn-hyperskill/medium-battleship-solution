package battleship.board;

import battleship.board.exception.EmplacingShipOnCtrlZoneException;
import battleship.board.locs.BoardCoordinates;
import battleship.cell.ShipCell;
import battleship.ship.AnchoredShip;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h6>Protagonist's Board</h6>
 */
public final class ProtagoBoard extends Board {

  // Instance's fields
  private final List<AnchoredShip> aliveShips = new ArrayList();
  private final Set<BoardCoordinates> ctrlZones = new HashSet<>();

  // CRUD-C
  public static ProtagoBoard empty() {
    return new ProtagoBoard();
  }

  protected ProtagoBoard() {
    super();
  }

  // CRUD-U
  public void emplaceShip(AnchoredShip incomingShip)
      throws EmplacingShipOnCtrlZoneException {
    // Check if the incomingShip can be placed
    incomingShip.hitBoxesStream()
        .forEach(ctrlZone -> {
          if (this.ctrlZones.contains(ctrlZone)) {
            throw new EmplacingShipOnCtrlZoneException(incomingShip, ctrlZone);
          }
        });
    // Put every incomingShip part or dependency.
    this.aliveShips.add(incomingShip);
    incomingShip.hitBoxesStream().forEach(hitBox -> this.cellsMtx.setValAt(
        hitBox, new ShipCell(incomingShip)));
    incomingShip.ctrlZonesStream()
        .forEach(ctrlZone -> this.ctrlZones.add(ctrlZone));
  }
}
