package battleship.shot;

import battleship.cell.Cell;
import battleship.ship.Ship;

public abstract class ShotResult {

  // CRUD-C: Factory methods

  public static final ShotMissed missed() {
    return ShotMissed.INSTANCE;
  }

  public static final ShotHit hit() {
    return ShotHit.INSTANCE;
  }

  public static final ShotDamaged damaged(Ship victim){
    return new ShotDamaged(victim);
  }
  public static final ShotSunk sunk(Ship victim) {
    return new ShotSunk(victim);
  }

  // CRUD-R: Properties

  public abstract Cell transformedCell();

  public abstract String msgForUser();
}

