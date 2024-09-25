package battleship.board;

import battleship.ship.Ship;

public final class EmplacingShipOnCtrlZoneException
    extends ShipPlacementException {

  // CRUD-C
  public EmplacingShipOnCtrlZoneException(Ship ship,
      BoardCoordinates ctrlZoneCoords) {
    super(String.format("""
        An incoming ship can't be emplaced\
         on an existing control zone.
        Was placing a part of `%s`
          on coordinates `%s`.
        """, ship, ctrlZoneCoords));
  }

  // CRUD-R
  @Override public String msgForUser() {
    return "Error! You placed it too close to another one.";
  }
}
