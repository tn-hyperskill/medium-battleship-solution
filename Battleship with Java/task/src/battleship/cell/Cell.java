package battleship.cell;

import battleship.ship.shot.ShotResult;

public abstract class Cell {

  // Instance field
  public final char symbol;

  // CRUD-C: Fabrication methods for subclass singletons

  public static FogCell fog() {
    return FogCell.INSTANCE;
  }

  public static HitCell hit() {
    return HitCell.INSTANCE;
  }

  public static MissCell miss() {
    return MissCell.INSTANCE;
  }

  public static SunkenCell sunken() {
    return SunkenCell.INSTANCE;
  }

  public static WaterCell water() {
    return WaterCell.INSTANCE;
  }

  // CRUD-C

  protected Cell(char symbol) {
    super();
    this.symbol = symbol;
  }

  public static Cell[] variants() {
    return new Cell[]{fog(), hit(), miss(), sunken(), water(),
        ShipCell.UNOWNED};
  }

  // CRUD-R

  public ShotResult takeShot() {
    return ShotResult.missed();
  }

  @Override
  public String toString() {
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
