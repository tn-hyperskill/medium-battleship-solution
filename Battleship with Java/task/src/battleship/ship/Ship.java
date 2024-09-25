package battleship.ship;

public class Ship {
  // Instance fields
  private final ShipModel model;
  private int hpLeft;

  public Ship(ShipModel model) {
    this.model = model;
    this.hpLeft = this.hpMax();
  }

  // CRUD-R: Getters – Direct

  public final ShipModel model(){
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

  public final ShipSize size(){
    return this.model().size;
  }

  // CRUD-R: Misc

  @Override
  public String toString(){
    return this.model().toString();
  }
}
