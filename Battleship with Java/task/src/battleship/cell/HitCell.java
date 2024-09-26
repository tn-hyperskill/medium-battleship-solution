package battleship.cell;

import battleship.ship.shot.ShotResult;

class HitCell extends Cell {

  // Singleton instance
  public static final HitCell INSTANCE = new HitCell();

  // CRUD-C

  private HitCell() {
    this('X');
  }

  protected HitCell(char symbol) {
    super(symbol);
  }

  // CRUD-R

  @Override public ShotResult takeShot() {
    return ShotResult.hit();
  }
}
