package battleship.cell;

import battleship.shot.ShotResult;

class HitCell extends Cell {

  // Singleton instance
  public static final HitCell INSTANCE = new HitCell();

  // CRUD-C

  protected HitCell() {
    super('X');
  }

  // CRUD-R

  @Override public ShotResult takeShot() {
    return ShotResult.hit();
  }
}
