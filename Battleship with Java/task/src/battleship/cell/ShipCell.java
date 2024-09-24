package battleship.cell;

import battleship.ship.AnchoredShip;

public final class ShipCell extends Cell {
  public final AnchoredShip owner;

  // Constructor with owner as parameter
  public ShipCell(final AnchoredShip owner) {
    super('O');
    this.owner = owner;
  }
}
