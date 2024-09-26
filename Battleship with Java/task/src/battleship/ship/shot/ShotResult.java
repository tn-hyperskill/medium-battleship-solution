package battleship.ship.shot;

import battleship.cell.Cell;
import battleship.ship.Ship;

public abstract class ShotResult {

  // CRUD-C: Factory methods

  public static final ShotMissed missed() {
    return ShotMissed.INSTANCE;
  }

  public static final ShotRehit rehit() {
    return ShotRehit.INSTANCE;
  }

  public static final ShotDamaged damaged(Ship victim){
    return new ShotDamaged(victim);
  }
  public static final ShotSunk sunk(Ship victim) {
    return new ShotSunk(victim);
  }

  // CRUD-R: Properties

  public abstract Cell transformedCell();

}

// Singletons

final class ShotMissed extends ShotResult {

  public static final ShotMissed INSTANCE = new ShotMissed();

  // CRUD-C

  private ShotMissed() {
  }

  // CRUD-R

  @Override public Cell transformedCell() {
    return Cell.miss();
  }
}

final class ShotRehit extends ShotResult {

  public static final ShotRehit INSTANCE = new ShotRehit();

  // CRUD-C

  private ShotRehit() {
  }

  // CRUD-R

  @Override public Cell transformedCell() {
    return Cell.sunken();
  }
}

