package battleship.ship.model;

public enum ShipSize {
  TINY(2), MEDIUM(3), BIG(4), LARGE(5);

  // Instance fields
  private final int volume;

  // CRUD-C

  public static ShipSize fromVolume(int volume) {
    // Iterate over all enum values and find the matching volume.
    for (ShipSize size : ShipSize.values()) {
      if (size.volume == volume) {
        return size;
      }
    }
    throw new IllegalArgumentException(
        "No ShipSize found for volume: " + volume);
  }

  ShipSize(int volume) {
    if (volume <= 0) {
      throw new IllegalArgumentException("""
          Currently 0 is not a valid volume. \
          A ship must be at least thin (width >= 1).
          """
      );
    }
    this.volume = volume;
  }

  // CRUD-R: Displayers

  @Override
  public String toString() {
    String name = this.capitalizedName();
    return String.format("%s(%d)", name, this.volume);
  }

  private String capitalizedName() {
    return this.name().charAt(0) + this.name().substring(1).toLowerCase();
  }

  // CRUD-R: Getters

  public int volume() {
    return this.volume;
  }
}
