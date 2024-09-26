package battleship.ship.shot;

import battleship.cell.Cell;
import battleship.ship.Ship;

public class ShotDamaged extends ShotResult {

  private final Ship victim;

  // CRUD-C

  public ShotDamaged(Ship victim) {
    this.victim = victim;
  }

  // CRUD-R

  public final Ship victim() {
    return this.victim;
  }

  @Override public Cell transformedCell() {
    return Cell.sunken();
  }
}
