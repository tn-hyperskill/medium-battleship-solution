package battleship.cell;

import battleship.ship.Ship;
import battleship.shot.ShotResult;

public final class ShipCell extends Cell {
  public static final char SYMBOL = 'O';
  public static final ShipCell UNOWNED = new ShipCell();

  public final Ship owner;

  // CRUD-C

  public ShipCell(final Ship owner) {
    super(SYMBOL);
    if (owner == null){
      throw new IllegalArgumentException("`owner` should not be `null`");
    }
    this.owner = owner;
  }

  private ShipCell(){
    super(SYMBOL);
    this.owner = null;
  }

  // CRUD-R

  @Override
  public ShotResult takeShot(){
    return this.owner.takeShot();
  }
}
