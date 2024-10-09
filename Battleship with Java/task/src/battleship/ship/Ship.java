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
  public final String toString() {
    return this.model().toString();
  }

  // CRUD-U

  /**
   * <h6>Take a shot</h6>
   * <br>
   * Receives a shot (from opponent). Updating the state (HP) to reflect the
   * damage.
   *
   * @return the shot result.
   */
  public final ShotResult takeShot() {
    this.receiveDamage();
    return this.isDead()
        ? ShotResult.sunk(this)
        : ShotResult.damaged(this);
  }

  protected final void receiveDamage() {
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
