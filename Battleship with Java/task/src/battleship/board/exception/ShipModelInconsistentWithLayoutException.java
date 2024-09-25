package battleship.board.exception;

import battleship.ship.ShipModel;

public class ShipModelInconsistentWithLayoutException extends ShipPlacementException {

  private final ShipModel model;

  public ShipModelInconsistentWithLayoutException(ShipModel model, int expectedVolume) {
    super(String.format("""
            Provided ship model is inconsistent \
            with the hit boxes implied by layout.
            `model.volume()` = %s;  `hit box (count) volume` = %s;
            """, model.volume(), expectedVolume));
    this.model = model;
  }

  // CRUD-R

  public ShipModel model() {
    return model;
  }

  @Override public String msgForUser() {
    return "Error! Wrong length of the %s!".formatted(this.model);
  }
}
