package battleship.cell;

import battleship.ship.shot.ShotResult;

final class SunkenCell extends Cell {

  // Singleton instance

  public static final SunkenCell INSTANCE = new SunkenCell();

  // CRUD-C

  private SunkenCell() {
    super('X');
  }

  // CRUD-R

  @Override public ShotResult takeShot(){
    return ShotResult.rehit();
  }
}
