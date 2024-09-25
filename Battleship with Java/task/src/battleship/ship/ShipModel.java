package battleship.ship;

import java.util.Arrays;
import java.util.Collections;

public enum ShipModel {
  DESTROYER("Destroyer", ShipSize.TINY), CRUISER("Cruiser", ShipSize.MEDIUM),
  SUBMARINE("Submarine", ShipSize.MEDIUM), BATTLESHIP("Battleship", ShipSize.BIG),
  AIRCRAFT_CARRIER("Aircraft Carrier", ShipSize.LARGE),
  ;
  // Instance fields
  public final String titleCaseName;
  public final ShipSize size;

  // CRUD-C

  ShipModel(String titleCaseName, ShipSize size) {
    this.titleCaseName = titleCaseName;
    this.size = size;
  }

  // CRUD-R: Properties

  public static ShipModel[] variantsInPlacingOrd(){
    var variants = values();
    Collections.reverse(Arrays.asList(variants));
    return variants;
  }

  @Override
  public String toString(){
    return this.titleCaseName;
  }

  // CRUD-R: Getters

  public int volume(){
    return this.size.volume();
  }
}
