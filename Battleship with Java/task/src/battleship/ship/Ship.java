package battleship.ship;

import battleship.board.Coordinate;

public final class Ship {
  // Instance fields
  public final ShipSize size;
  public final Coordinate start, finish;

  public Ship(ShipSize size, Coordinate start, Coordinate finish) {
    this.size = size;
    this.start = start;
    this.finish = finish;

    if (!this.isThin()){
      // Currently fat ships are not tolerated by the game rules.
      throw new IllegalArgumentException();
    }
  }

  private boolean isThin(){
    return this.start.row == this.finish.row || this.start.col == this.finish.col;
  }
}
