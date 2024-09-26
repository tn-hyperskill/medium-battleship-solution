package battleship.ship.shot;

import battleship.ship.Ship;

public class ShotSunk extends ShotDamaged {
  ShotSunk(Ship victim) {
    super(victim);
  }
}
