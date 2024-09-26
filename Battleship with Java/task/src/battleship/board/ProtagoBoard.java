package battleship.board;

import battleship.board.exception.EmplacingShipOnCtrlZoneException;
import battleship.board.locs.BoardCoordinates;
import battleship.cell.Cell;
import battleship.cell.ShipCell;
import battleship.ship.AnchoredShip;
import battleship.ship.shot.ShotDamaged;
import battleship.ship.shot.ShotResult;
import battleship.ship.shot.ShotSunk;
import java.util.HashSet;
import java.util.Set;

/**
 * <h6>Protagonist's Board</h6>
 */
public final class ProtagoBoard extends Board {

  // Instance's fields
  private final Set<AnchoredShip> aliveShips = new HashSet<>();
  private final Set<BoardCoordinates> ctrlZones = new HashSet<>();

  // CRUD-C
  public static ProtagoBoard empty() {
    return new ProtagoBoard();
  }

  protected ProtagoBoard() {
    super(Cell.water());
  }

  // CRUD-R

  public boolean hasAliveShips(){
    return !this.aliveShips.isEmpty();
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

  public ShotResult takeShotAt(final BoardCoordinates coordinates) {
    final var shotRes = this.cellsMtx.valAt(coordinates).takeShot();
    this.cellsMtx.setValAt(coordinates, shotRes.transformedCell());

    if (shotRes instanceof ShotSunk sunk){
      this.aliveShips.remove(sunk.victim());
    }

    return shotRes;
  }
}
