package battleship.shot;

import battleship.cell.Cell;

public final class ShotMissed extends ShotResult {

  public static final ShotMissed INSTANCE = new ShotMissed();

  // CRUD-C

  private ShotMissed() {
  }

  // CRUD-R

  @Override public Cell transformedCell() {
    return Cell.miss();
  }

  @Override public String msgForUser() {
    return "You missed!";
  }


}
