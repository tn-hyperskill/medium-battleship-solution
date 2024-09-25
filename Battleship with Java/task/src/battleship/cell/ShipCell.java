package battleship.cell;

import battleship.ship.AnchoredShip;

public final class ShipCell extends Cell {
  public static final char SYMBOL = 'O';
  public static final ShipCell UNOWNED = new ShipCell();

  public final AnchoredShip owner;

  // Constructor with owner as parameter
  public ShipCell(final AnchoredShip owner) {
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
}
