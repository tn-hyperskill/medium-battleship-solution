package battleship.ship;

public enum ShipModel {
  DESTROYER("Destroyer", ShipSize.TINY), CRUISER("Cruiser", ShipSize.MEDIUM),
  SUBMARINE("Submarine", ShipSize.MEDIUM), BATTLESHIP("Battleship", ShipSize.BIG),
  AIRCRAFT_CARRIER("Aircraft Carrier", ShipSize.LARGE),
  ;
  public final String titleCaseName;
  public final ShipSize shipSize;

  ShipModel(String titleCaseName, ShipSize shipSize) {
    this.titleCaseName = titleCaseName;
    this.shipSize = shipSize;
  }
}
