package battleship.shot;

import battleship.ship.Ship;

public class ShotDamaged extends ShotHit {

  private final Ship victim;

  // CRUD-C

  public ShotDamaged(Ship victim) {
    super();
    this.victim = victim;
  }

  // CRUD-R

  public final Ship victim() {
    return this.victim;
  }
}
