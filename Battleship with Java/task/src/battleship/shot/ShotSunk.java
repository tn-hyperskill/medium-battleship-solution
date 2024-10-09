package battleship.shot;

import battleship.cell.Cell;
import battleship.ship.Ship;

public class ShotSunk extends ShotDamaged {

  ShotSunk(Ship victim) {
    super(victim);
  }

  @Override public Cell transformedCell() {
    return Cell.sunken();
  }

  @Override
  public String msgForUser() {
    return "You sank a ship!";
  }
}
