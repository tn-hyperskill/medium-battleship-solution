package battleship.ship;

public enum ShipSize {
  TINY(2), MEDIUM(3), BIG(4), LARGE(5);

  // Instance fields
  private final int mass;

  // CRUD-C

  ShipSize(int mass){
    this.mass = mass;
  }
}
