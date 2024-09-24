package battleship.cell;

public abstract class Cell {
  // Instance field
  public final char symbol;

  // Fabrication methods for singletons
  public static FogCell fog() {
    return FogCell.INSTANCE;
  }

  public static HitCell hit() {
    return HitCell.INSTANCE;
  }

  public static MissCell miss() {
    return MissCell.INSTANCE;
  }

  public static SunkCell sunk() {
    return SunkCell.INSTANCE;
  }

  public static WaterCell water() {
    return WaterCell.INSTANCE;
  }

  // Protected constructor to be used by subclasses
  protected Cell(char symbol) {
    this.symbol = symbol;
  }

  // CRUD-R
  @Override
  public String toString() {
    return String.valueOf(this.symbol);
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

final class HitCell extends Cell {
  // Singleton instance
  public static final HitCell INSTANCE = new HitCell();

  // Private constructor to prevent external instantiation
  private HitCell() {
    super('@');
  }
}

final class MissCell extends Cell {
  // Singleton instance
  public static final MissCell INSTANCE = new MissCell();

  // Private constructor to prevent external instantiation
  private MissCell() {
    super('*');
  }
}

final class SunkCell extends Cell {
  // Singleton instance
  public static final SunkCell INSTANCE = new SunkCell();

  // Private constructor to prevent external instantiation
  private SunkCell() {
    super('$');
  }
}

final class WaterCell extends Cell {
  // Singleton instance
  public static final WaterCell INSTANCE = new WaterCell();

  // Private constructor to prevent external instantiation
  private WaterCell() {
    super('≈');
  }
}
