package battleship.shot;

import battleship.ship.Ship;

public class ShotSunk extends ShotDamaged {
  ShotSunk(Ship victim) {
    super(victim);
  }

  @Override
  public String msgForUser(){
    return "You sank a ship!";
  }
}
