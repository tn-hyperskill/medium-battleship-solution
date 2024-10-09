package battleship.shot;

import battleship.cell.Cell;

public class ShotHit extends ShotResult {

  public static final ShotHit INSTANCE = new ShotHit();

  // CRUD-C

  protected ShotHit() {
  }

  // CRUD-R

  @Override public Cell transformedCell() {
    return Cell.hit();
  }

  @Override public String msgForUser() {
    return "You hit a ship!";
  }
}
