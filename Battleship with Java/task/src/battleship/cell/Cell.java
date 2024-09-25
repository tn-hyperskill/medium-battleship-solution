package battleship.cell;

public abstract class Cell extends CellParser {
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

  public static SunkCell sunk() {
    return SunkCell.INSTANCE;
  }

  public static WaterCell water() {
    return WaterCell.INSTANCE;
  }

  // CRUD-C

  protected Cell(char symbol) {
    super();
    this.symbol = symbol;
  }

  public static Cell[] variants(){
    return new Cell[]{fog(), hit(), miss(), sunk(), water(), ShipCell.UNOWNED};
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
    super('X');
  }
}

final class MissCell extends Cell {
  // Singleton instance
  public static final MissCell INSTANCE = new MissCell();

  // Private constructor to prevent external instantiation
  private MissCell() {
    super('M');
  }
}

final class SunkCell extends Cell {
  // Singleton instance
  public static final SunkCell INSTANCE = new SunkCell();

  // Private constructor to prevent external instantiation
  private SunkCell() {
    super('X');
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
