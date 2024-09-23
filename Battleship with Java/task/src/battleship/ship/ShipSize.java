package battleship.ship;

public enum ShipSize {
  TINY(2), MEDIUM(3), BIG(4), LARGE(5);

  // Instance fields
  public final int volume;

  // CRUD-C

  ShipSize(int volume){
    if (volume <= 0){
      throw new IllegalArgumentException("""
        Currently 0 is not a valid volume. \
        A ship must be at least thin (width >= 1).
        """
      );
    }
    this.volume = volume;
  }
}
