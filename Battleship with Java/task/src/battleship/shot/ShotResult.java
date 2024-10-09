package battleship.shot;

import battleship.cell.Cell;
import battleship.ship.Ship;

@SuppressWarnings("SameReturnValue") public abstract class ShotResult {

  // CRUD-C: Factory methods

  public static ShotMissed missed() {
    return ShotMissed.INSTANCE;
  }

  public static ShotHit hit() {
    return ShotHit.INSTANCE;
  }

  public static ShotDamaged damaged(Ship victim) {
    return new ShotDamaged(victim);
  }

  public static ShotSunk sunk(Ship victim) {
    return new ShotSunk(victim);
  }

  // CRUD-R: Properties

  /**
   * @return the cell that should be put in the place of the shot cell due to
   * transformative effect of being shot.
   */
  public abstract Cell transformedCell();

  /**
   * @return the message for an end user.
   */
  public abstract String msgForUser();
}

