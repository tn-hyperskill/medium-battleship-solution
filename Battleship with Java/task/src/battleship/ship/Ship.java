package battleship.ship;

import battleship.ship.model.ShipModel;
import battleship.ship.model.ShipSize;
import battleship.shot.ShotResult;

public class Ship {

  // Instance fields
  private final ShipModel model;
  private int hpLeft;

  public Ship(ShipModel model) {
    this.model = model;
    this.hpLeft = this.hpMax();
  }

  // CRUD-R: Properties

  public final boolean isDead() {
    return this.hpLeft() <= 0;
  }

  // CRUD-R: Getters – Direct

  public final ShipModel model() {
    return this.model;
  }

  public final int hpLeft() {
    return this.hpLeft;
  }

  // CRUD-R: Getters – Delegate

  public final int hpMax() {
    return this.volume();
  }

  public final int volume() {
    return this.size().volume();
  }

  public final ShipSize size() {
    return this.model().size;
  }

  // CRUD-R: Misc

  @Override
  public String toString() {
    return this.model().toString();
  }

  // CRUD-U

  public ShotResult takeShot() {
    this.takeDamage();
    return this.isDead() ?
        ShotResult.sunk(this) :
        ShotResult.damaged(this);
  }

  protected void takeDamage() {
    if (this.isDead()) {
      throw new UnsupportedOperationException(
          """
              Attempted damaging cell of already dead ship.\
               This cell should be underwater (unavailable for shooting).
              """
      );
    }
    this.hpLeft--;
  }
}
