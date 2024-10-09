package battleship.cell;

import battleship.shot.ShotResult;

@SuppressWarnings({"PMD.AbstractClassWithoutAbstractMethod", "SameReturnValue"})
public abstract class Cell {

  // Instance field
  public final char symbol;

  // CRUD-C: Fabrication methods for subclass singletons

  public static Cell fog() {
    return FogCell.INSTANCE;
  }

  @SuppressWarnings("unused")
  public static Cell hit() {
    return HitCell.INSTANCE;
  }

  public static Cell miss() {
    return MissCell.INSTANCE;
  }

  public static Cell sunken() {
    return SunkenCell.INSTANCE;
  }

  public static Cell water() {
    return WaterCell.INSTANCE;
  }

  // CRUD-C

  protected Cell(char symbol) {
    super();
    this.symbol = symbol;
  }

  // CRUD-R

  /**
   * <h6>Take a shot</h6>
   * <br>
   * This cell receives a shot (from opponent). Because {@link Cell} is
   * inherently immutable object, it should be updated by reassignment. The
   * returned value
   * <b>indicates whether the cell needs (reassignment) replacement</b> and what
   * kind of.
   *
   * @return the shot result indicating potential need of reassignment.
   */
  public ShotResult takeShot() {
    return ShotResult.missed();
  }

  @Override
  public final String toString() {
    return String.valueOf(this.symbol);
  }
}

// Subclasses

final class MissCell extends Cell {

  // Singleton instance
  public static final MissCell INSTANCE = new MissCell();

  // Private constructor to prevent external instantiation
  private MissCell() {
    super('M');
  }
}

final class FogCell extends Cell {

  // Singleton instance
  public static final FogCell INSTANCE = new FogCell();

  // Private constructor to prevent external instantiation
  private FogCell() {
    super('~');
  }
}

final class WaterCell extends Cell {

  // Singleton instance
  public static final WaterCell INSTANCE = new WaterCell();

  // Private constructor to prevent external instantiation
  private WaterCell() {
    super('~');
  }
}
