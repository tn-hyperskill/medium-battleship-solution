package battleship.cell;

final class SunkenCell extends HitCell {

  // Singleton instance

  public static final SunkenCell INSTANCE = new SunkenCell();

  // CRUD-C

  private SunkenCell() {
    super('X');
  }

}
